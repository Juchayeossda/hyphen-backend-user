package com.get.hyphenbackenduser.domain.user.service;

import com.get.hyphenbackenduser.domain.user.presentation.dto.response.MyInfoResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.ReimageResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.RenamedResponse;
import com.get.hyphenbackenduser.domain.user.presentation.dto.response.WithdrawalUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    ResponseEntity<Resource> getImage(HttpServletRequest request) throws IOException;
    MyInfoResponse getInfo();
    RenamedResponse rename(String newName);
    ReimageResponse reimage(MultipartFile imageFile);
    WithdrawalUserResponse withdrawal();
}
