package com.JYProject.project.service;

import com.JYProject.project.model.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    int insertMember(MemberDTO memberDTO);
    MemberDTO selectMemberDetail(String loginId);
    MemberDTO login(MemberDTO memberDTO);

    public boolean validCheckId(String loginId);
    int updateMember(MemberDTO memberDTO);
    int deleteMember(Long id);
    List<MemberDTO> MemberAllList();
    int selectMemberTotalCount();
    public boolean checkIdAndPw(MemberDTO memberDTO);
}
