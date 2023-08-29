package com.get.hyphenbackenduser.domain.user.domain.repository;

import com.get.hyphenbackenduser.domain.user.domain.entity.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    Optional<UserImage> findByUid(String uid);
}
