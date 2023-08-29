package com.get.hyphenbackenduser.domain.user.domain.entity;

import com.get.hyphenbackenduser.domain.user.presentation.dto.Image;
import com.get.hyphenbackenduser.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "user_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@SuperBuilder
public class UserImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uid;

    @Embedded
    private Image image;
}
