package com.JYProject.project.model.dto;

import com.JYProject.project.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("유저PK")
    @Column(name="member_id")
    private Long id;

    @Column(unique = true)
    @Comment("아이디")
    private String loginId;

    @Column
    @Comment("비밀번호")
    private String pw;

    @Column
    @Comment("이름")
    private String name;

    @Column(unique = true)
    @Comment("닉네임")
    private String nickname;

    @Column(unique = true)
    @Comment("핸드폰번호")
    private String phone;

    @Column(unique = true)
    @Comment("이메일")
    private String email;

    @Column
    @Comment("우편번호")
    private String zipCode;

    @Column
    @Comment("주소")
    private String address;

    @Column
    @Comment("상세주소")
    private String addressDetail;

    @Column
    @Comment("사진")
    private String profilePic;

    @Column
    @Comment("로그인일자")
    private LocalDateTime lastLoginDate;

    @Column
    @Comment("회원 계정 비활성화 or 활성화 여부")
    private Boolean active;

    @Column
    @Comment("등급")
    private String  Grade;

    @Column
    @Comment("포인트")
    private Long point;


}
