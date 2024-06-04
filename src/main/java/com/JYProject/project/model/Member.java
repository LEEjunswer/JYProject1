package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member{

    //("유저PK")
    private Long memberId;
    //("아이디")
    private String loginId;
    //("비밀번호")
    private String pw;
    //("이름")
    private String name;
    //("닉네임")
    private String nickname;
    //("핸드폰번호")
    private String phone;
    //("이메일")
    private String email;
    //("우편번호")
    private String zipCode;
    //("주소")
    private String address;
    //("상세주소")
    private String addressDetail;
    //("회원가입일자")
    private LocalDateTime regDate;
    //("로그인일자")
    private LocalDateTime lastLoginDate;
    //("회원 계정 비활성화 or 활성화 여부 좀고민이다 정지나 영구정지를 먹였을떄는 탈퇴를 못하게 막아야하는데")
    private Boolean active;
    //("등급")
    private String  Grade;
    //("포인트")
    private int point;
    //("프로필이미지")
    private String profileImg;

}
