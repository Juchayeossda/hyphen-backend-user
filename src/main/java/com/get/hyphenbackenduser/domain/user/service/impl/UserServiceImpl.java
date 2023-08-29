package com.get.hyphenbackenduser.domain.user.service.impl;

import com.get.hyphenbackenduser.domain.auth.exception.AlreadyWithdrawnUserException;
import com.get.hyphenbackenduser.domain.user.exception.ImageNullException;
import com.get.hyphenbackenduser.domain.user.presentation.dto.Image;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.domain.user.domain.entity.UserImage;
import com.get.hyphenbackenduser.domain.user.domain.repository.UserImageRepository;
import com.get.hyphenbackenduser.domain.user.domain.repository.UserRepository;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import com.get.hyphenbackenduser.domain.user.exception.AlreadyNameExistsException;
import com.get.hyphenbackenduser.domain.user.exception.UserDeactivatedException;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.MyInfoResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.ReimageResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.RenamedResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.WithdrawalUserResponse;
import com.get.hyphenbackenduser.domain.user.service.StorageService;
import com.get.hyphenbackenduser.domain.user.service.UserService;
import com.get.hyphenbackenduser.global.lib.security.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SecurityService securityService;
    private final UserRepository userRepository;
    private final UserImageRepository userImageRepository;
    private final StorageService storageService;

    @Transactional
    public ResponseEntity<Resource> getImage(HttpServletRequest httpServletRequest) throws IOException {
        User user = securityService.getAuthUserInfo();
        String fileName = userImageRepository.findByUid(user.getUid()).get().getImage().getName();
        Resource resource = storageService.loadFileAsResource(user.getUid(), fileName);
        String contentType = null;
        try {
            contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new IOException(e);
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Transactional
    public MyInfoResponse getInfo() {
        User user = securityService.getAuthUserInfo();
        return MyInfoResponse.builder()
                .uid(user.getUid())
                .name(user.getName())
                .email(user.getEmail())
                .userStatus(user.getUserStatus())
                .socialType(user.getSocialType())
                .socialId(user.getSocialId())
                .build();
    }

    @Transactional
    public RenamedResponse rename(String newName) {
        User user = securityService.getAuthUserInfo();
        String originName = user.getName();
        if (user.getUserStatus().equals(UserStatus.BANNED)) {
            throw UserDeactivatedException.EXCEPTION;
        }
        if (userRepository.findByName(newName).isPresent()) {
            throw AlreadyNameExistsException.EXCEPTION;
        }
        user.setName(newName);
        userRepository.save(user);
        return RenamedResponse.builder()
                .uid(user.getUid())
                .originName(originName)
                .newName(user.getName())
                .build();
    }

    @Transactional
    public ReimageResponse reimage(MultipartFile imageFile) {
        User user = securityService.getAuthUserInfo();
        if (user.getUserStatus().equals(UserStatus.BANNED)) {
            throw UserDeactivatedException.EXCEPTION;
        }
        if (imageFile == null) {
            throw ImageNullException.EXCEPTION;
        }
        try {
            String originFileName = userImageRepository.findByUid(user.getUid()).get().getImage().getName();
            List<String> saveResult = storageService.saveFile(imageFile, user.getUid(), originFileName);
            String imageName = saveResult.get(1);
            String imagePath = saveResult.get(0);
            if (userImageRepository.findByUid(user.getUid()).isPresent()) {
                UserImage userImage = userImageRepository.findByUid(user.getUid()).get();
                userImage.setImage(Image.builder()
                                    .name(imageName)
                                    .storagePath(imagePath)
                                    .build());
                userImageRepository.save(userImage);
            } else {
                userImageRepository.save(UserImage.builder()
                        .uid(user.getUid())
                        .image(Image.builder()
                                .name(imageName)
                                .storagePath(imagePath)
                                .build())
                        .build());
            }
            UserImage userImage = userImageRepository.findByUid(user.getUid()).get();
            user.setImageId(userImage.getId());
            userRepository.save(user);
            return ReimageResponse.builder()
                    .uid(user.getUid())
                    .imageId(userImage.getId())
                    .imageName(imageName)
                    .imagePath(imagePath)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public WithdrawalUserResponse withdrawal() {
        User user = securityService.getAuthUserInfo();
        if (user.getUserStatus().equals(UserStatus.BANNED)) {
            throw AlreadyWithdrawnUserException.EXCEPTION;
        }
        user.setUserStatus(UserStatus.BANNED);
        user.setDeletedDateTime(LocalDateTime.now());
        userRepository.save(user);
        return WithdrawalUserResponse.builder()
                .uid(user.getUid())
                .userStatus(user.getUserStatus())
                .userRole(user.getUserRole())
                .build();
    }
}
