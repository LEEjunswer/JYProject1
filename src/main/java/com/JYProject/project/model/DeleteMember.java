package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMember {
    /*deleteMember fk*/
    private Long deletedId;
    /*로그인아이디*/
    private String loginId;
    /*성 이름*/
    private String name;
    /*가입시 번호*/
    private String phone;
    /*가입시 이메일*/
    private String email;
    /*우편번호*/
    private String zipcode;
    /*집주소*/
    private String address;
    /*집주소 자세히*/
    private String addressDetail;
    /*초기 회원가입등록일*/
    private LocalDateTime regDate;
    /*회원 탈퇴일자 1년 보관하고 삭제시킨다*/
    private LocalDateTime deletedDate;
}
