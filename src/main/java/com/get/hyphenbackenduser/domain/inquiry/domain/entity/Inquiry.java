package com.get.hyphenbackenduser.domain.inquiry.domain.entity;

import com.get.hyphenbackenduser.domain.inquiry.enums.InquiryStatus;
import com.get.hyphenbackenduser.global.jpa.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Schema(title = "inquiry: 문의 정보 Entity", description = "문의 정보 객체")
@Entity
@Table(name = "inquiry")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@SuperBuilder
public class Inquiry extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "사용자 아이디", nullable = false, maxLength = 50)
    @Column(nullable = false, length = 50, unique = true)
    private String uid;

    @Schema(description = "문의 상태", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private InquiryStatus inquiryStatus;

    @Schema(description = "문의 제목", nullable = false)
    @Column(nullable = false)
    private String title;

    @Schema(description = "문의 내용", nullable = false)
    @Column(nullable = false)
    private String content;

    @Schema(description = "문의 답변", nullable = true)
    @Column(nullable = true)
    private String answer;
}
