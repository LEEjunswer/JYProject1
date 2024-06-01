package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberMapperRepository {

     int insertMember(Member member);
    Member getOneMemberId(Long id);
     Member login(Member member);
     Member selectMemberDetail(String loginId);

    int updateMember(MemberDTO memberDTO);

    boolean validCheckId(String loginId);
    boolean validCheckNick(String nickname);
    boolean validCheckEmail(String email);
    int deleteMember(Long id);

    List<Member> MemberAllList();

    int selectMemberTotalCount();
    boolean checkIdAndPw(Member member);
    void updateProfileImg(String loginId , String profileImg);

}
