package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("유저PK")
    private Long memberNo;
    @Column
    @Comment("아이디")
    private String loginId;
    @Column
    @Comment("비밀번호")
    private String pw;
    @Column
    @Comment("이름")
    private String name;
    @Column
    @Comment("닉네임")
    private String nickname;
    @Column
    @Comment("핸드폰번호")
    private String phone;
    @Column
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
    @Comment("회원가입일자")
    private LocalDateTime regDate;
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
