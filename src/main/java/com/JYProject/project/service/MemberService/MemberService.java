package com.JYProject.project.service.MemberService;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    int insertMember(MemberDTO memberDTO);
    MemberDTO getOneMemberId(Long id);
    MemberDTO selectMemberDetail(String loginId);
    MemberDTO login(MemberDTO memberDTO);

    boolean validCheckId(String loginId);
    boolean validCheckNick(String nickname);
    boolean validCheckEmail(String email);
    int changePassword(Long memberId, String password);
    int changeNickname(Long memberId, String nickname);
     int deleteMember(MemberDTO memberDTO);
    int addPointLikes(Long id);
    int addPointDisLikes(Long id);
     List<MemberDTO> MemberAllList();
     int selectMemberTotalCount();
     int updateMemberActive(Long memberId,boolean check);
     List<MemberDTO> getAllMemberListPaging(int page, int pageSize);
     int getChooseAdopt(MemberDTO memberDTO);
     boolean checkIdAndPw(MemberDTO memberDTO);
    void updateProfileImage(String loginId, MultipartFile profileImage) throws  IOException;
    int addApplicantEvent(MemberDTO memberDTO);
}
