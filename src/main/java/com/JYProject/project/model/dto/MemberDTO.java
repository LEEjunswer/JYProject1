package com.JYProject.project.model.dto;

<<<<<<< HEAD
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
=======
import com.JYProject.project.model.BaseEntity;
import com.JYProject.project.model.Member;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class MemberDTO extends BaseEntity {

    private Long id;
>>>>>>> main
    private String loginId;
    private String pw;
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String zipCode;
<<<<<<< HEAD
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
=======
    private String address;
    private String addressDetail;
    // private String profilePic;
    private LocalDateTime lastLoginDate;
    private Boolean active;
    private String Grade;

    public Member builder() {
        return Member.builder()
                .loginId(loginId)
                .pw(pw)
                .name(name)
                .nickname(nickname)
                .phone(phone)
                .email(email)
                .zipCode(zipCode)
                .address(address)
                .addressDetail(addressDetail)
                .point(0L)
                .build();
    }
>>>>>>> main

}
