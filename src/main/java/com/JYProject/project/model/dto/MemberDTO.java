package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO  {


    @Comment("유저PK")
    private Long memberId;


    @Comment("아이디")
    private String loginId;

    @Comment("비밀번호")
    private String pw;


    @Comment("이름")
    private String name;


    @Comment("닉네임")
    private String nickname;


    @Comment("핸드폰번호")
    private String phone;

    @Comment("이메일")
    private String email;

    @Comment("우편번호")
    private String zipCode;
    @Comment("주소")
    private String address;
    @Comment("상세주소")
    private String addressDetail;
    @Comment("회원가입일자")
    private LocalDateTime regDate;
    @Comment("로그인일자")
    private LocalDateTime lastLoginDate;
    @Comment("회원 계정 비활성화 or 활성화 여부")
    private Boolean active;
    @Comment("등급")
    private String  Grade;
    @Comment("포인트")
    private Long point;

    private String profileImg;

}
