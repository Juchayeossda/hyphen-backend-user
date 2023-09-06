package com.get.hyphenbackenduser.domain.user.service;

import com.get.hyphenbackenduser.domain.user.presentation.dto.response.*;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    GetProfileImageResponse getImage();
    MyInfoResponse getInfo();
    RenamedResponse rename(String newName);
    ReimageResponse reimage(MultipartFile imageFile);
    WithdrawalUserResponse withdrawal();
}
