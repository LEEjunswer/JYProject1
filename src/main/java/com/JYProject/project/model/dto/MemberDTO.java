package com.JYProject.project.model.dto;

import com.JYProject.project.model.BaseEntity;
import com.JYProject.project.model.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MemberDTO extends BaseEntity {

    private Long id;
    private String loginId;
    private String pw;
    private String name;
    private String nickname;
    private String phone;
    private String email;
    private String zipCode;
    private String address;
    private String addressDetail;
    // private String profilePic;
    private LocalDateTime lastLoginDate;
    private Boolean active;
    private String Grade;
    private Long point;

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

}
