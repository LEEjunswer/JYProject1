package com.JYProject.project.service;

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
     int updateMember(MemberDTO memberDTO);
     int deleteMember(Long id);
     List<MemberDTO> MemberAllList();
     int selectMemberTotalCount();
     boolean checkIdAndPw(MemberDTO memberDTO);
    void updateProfileImage(String loginId, MultipartFile profileImage,  String profile) throws  IOException;

}
