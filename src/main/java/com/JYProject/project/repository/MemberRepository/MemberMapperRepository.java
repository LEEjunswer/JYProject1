package com.JYProject.project.repository.MemberRepository;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapperRepository {
    int insertMember(Member member);
    Member getOneMemberId(Long id);
    Member login(Member member);
    Member selectMemberDetail(String loginId);
    int addLoginPoint (Member member);
    int updateMember(Member member);
    int updateLastLogin(Long id , LocalDateTime lastLoginDate);
    int addPointLikes(Long id);
    int updateMemberActive(Member member);
    int addPointDisLikes(Long id);
    boolean validCheckId(String loginId);
    boolean validCheckNick(String nickname);
    boolean validCheckEmail(String email);
    int deleteMember(Member member);
    int getChooseAdopt(Member member);
    List<Member> MemberAllList();
    List<Member> getAllMemberListPaging(Map<String,Object> params);
    int selectMemberTotalCount();
    boolean checkIdAndPw(Member member);
    void updateProfileImg(String loginId , String profileImg);
    int addApplicantEvent(Member member);
}
