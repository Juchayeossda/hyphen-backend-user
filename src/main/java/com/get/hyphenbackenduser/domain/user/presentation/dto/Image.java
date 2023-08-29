package com.get.hyphenbackenduser.domain.user.presentation.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Builder
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String storagePath;
}
