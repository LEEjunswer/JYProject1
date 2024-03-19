package com.JYProject.project.service;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.mybatis.MemberMybatisRepository;

import java.util.List;

public interface MemberSerivce {
    int insertMember(MemberDTO memberDTO);
    MemberDTO selectMemberDetail(Long id);
    MemberDTO login(MemberDTO memberDTO);

    public boolean validCheckId(String loginId);
    int updateMember(MemberDTO memberDTO);
    int deleteMember(Long id);
    List<MemberDTO> selectMemberList();
    int selectMemberTotalCount();
}
