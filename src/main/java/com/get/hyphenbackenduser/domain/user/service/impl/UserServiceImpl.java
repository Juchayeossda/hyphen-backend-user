package com.get.hyphenbackenduser.domain.user.service.impl;

import com.get.hyphenbackenduser.domain.auth.exception.AlreadyWithdrawnUserException;
import com.get.hyphenbackenduser.domain.user.exception.ImageNullException;
import com.get.hyphenbackenduser.domain.user.domain.entity.User;
import com.get.hyphenbackenduser.domain.user.domain.repository.UserRepository;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import com.get.hyphenbackenduser.domain.user.exception.AlreadyNameExistsException;
import com.get.hyphenbackenduser.domain.user.exception.UserDeactivatedException;
import com.get.hyphenbackenduser.domain.user.presentation.dto.request.UpdatePasswordRequest;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.*;
import com.get.hyphenbackenduser.domain.user.service.UserService;
import com.get.hyphenbackenduser.global.enums.Status;
import com.get.hyphenbackenduser.global.lib.security.service.SecurityService;
import com.get.hyphenbackenduser.global.lib.webClient.dto.response.ImageUploadResponse;
import com.get.hyphenbackenduser.global.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SecurityService securityService;
    private final UserRepository userRepository;
    private final WebClientUtil webClientUtil;
    private final PasswordEncoder passwordEncoder;

    public GetProfileImageResponse getImage() {
        User user = securityService.getAuthUserInfo();
        if (user.getImagePath() == null) {
            return GetProfileImageResponse.builder().build();
        }
        return GetProfileImageResponse.builder()
                .imageUrl(webClientUtil.getImageServerPath() + "/api/siss/storages/images/" + user.getImagePath())
                .build();
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
                .imagePath(user.getImagePath())
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
    public ReimageResponse reimage(MultipartFile image) {
        User user = securityService.getAuthUserInfo();
        if (user.getUserStatus().equals(UserStatus.BANNED)) {
            throw UserDeactivatedException.EXCEPTION;
        }
        if (image == null) {
            throw ImageNullException.EXCEPTION;
        }
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("multipart-file-image", image.getResource());
        ImageUploadResponse imageUploadResponse = webClientUtil.imageUpload(builder);
        if (imageUploadResponse.getCode() == 201) {
            user.setImagePath(imageUploadResponse.getData().getId());
            userRepository.save(user);
            return ReimageResponse.builder()
                    .status(Status.SUCCESS)
                    .description(imageUploadResponse.getMessage() + ", id: " + imageUploadResponse.getData().getId())
                    .build();
        }
        return ReimageResponse.builder()
                .status(Status.FAILURE)
                .description(imageUploadResponse.getMessage())
                .build();
    }

    @Transactional
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = securityService.getAuthUserInfo();
        if (passwordEncoder.matches(updatePasswordRequest.getOriginPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
            if (userRepository.save(user) != null) {
                return UpdatePasswordResponse.builder()
                        .status(Status.SUCCESS)
                        .build();
            }
        }
        return UpdatePasswordResponse.builder()
                .status(Status.FAILURE)
                .build();
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

    @Transactional
    public DeleteProfileImageResponse deleteImage() {
        User user = securityService.getAuthUserInfo();
        if (user.getUserStatus().equals(UserStatus.BANNED)) {
            throw UserDeactivatedException.EXCEPTION;
        }
        user.setImagePath(null);
        if (userRepository.save(user) != null) {
            return DeleteProfileImageResponse.builder()
                    .status(Status.SUCCESS)
                    .build();
        }
        return DeleteProfileImageResponse.builder()
                .status(Status.FAILURE)
                .build();
    }
}
