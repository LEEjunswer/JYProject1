package com.JYProject.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private int memberNo; //멤버번호
    private String id;   //아이디
    private String pw;  // 비밀번호
    private String name;  // 이름
    private String nickname; // 게시판 작성자나 댓글 활용
    private String phone; // 핸드폰 번호
    private String email; // 이메일
    private String zipcode; // 우편번호
    private String address; // 주소
    private String addressDetail; // 집주소 디테일
    private String member_profile; //유저 프로필 사진
    private Date regDate;  // 회원가입일자
    private Date lastLoginDate;  //마지막 로그인일자
    private Boolean active; // 회원 계정 비활성화 or 활성화 여부
    private String  Grade; // 유저 등급
    private int point; // 유저 포인트


}
