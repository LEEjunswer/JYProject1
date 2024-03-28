package com.JYProject.project.model;

import com.JYProject.project.model.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity{

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

   /* @Column
    @Comment("사진")
    private String profilePic;
*/
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

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();
    
    public void updateMemberInfo(MemberDTO memberDTO){
        loginId = memberDTO.getLoginId();
        pw = memberDTO.getPw();
        nickname = memberDTO.getNickname();
        ///// 알아서
    }
}