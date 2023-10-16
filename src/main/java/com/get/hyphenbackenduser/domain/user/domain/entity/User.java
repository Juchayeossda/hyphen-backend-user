package com.get.hyphenbackenduser.domain.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.get.hyphenbackenduser.domain.user.enums.SocialType;
import com.get.hyphenbackenduser.domain.user.enums.UserRole;
import com.get.hyphenbackenduser.domain.user.enums.UserStatus;
import com.get.hyphenbackenduser.global.jpa.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

@Schema(title = "User: 사용자 정보 Entity", description = "사용자 정보 객체")
@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@SuperBuilder
public class User extends BaseTimeEntity {

    @Schema(description = "식별 아이디", nullable = false)
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "사용자 아이디", nullable = false, maxLength = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String uid;

    @Schema(description = "사용자 이름", nullable = false, maxLength = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @Schema(description = "사용자 이메일", nullable = false)
    @Column(nullable = false, unique = true)
    private String email;

    @Schema(description = "사용자 패스워드", nullable = false, maxLength = 100)
    @JsonIgnore
    @Column(nullable = false, length = 100)
    private String password;

    @Schema(description = "사용자 상태", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus;

    @Schema(description = "사용자 권한", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Schema(description = "OAuth 타입", nullable = true)
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true)
    private SocialType socialType;

    @Schema(description = "OAuth 아이디", nullable = true)
    @Column(nullable = true)
    private String socialId;

    @Schema(description = "프로필 이미지 경로", nullable = true)
    @Column(nullable = true, unique = true)
    private String imagePath;
}
