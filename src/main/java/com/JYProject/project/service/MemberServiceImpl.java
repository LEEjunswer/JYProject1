package com.JYProject.project.service;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.mybatis.MemberMybatisMapperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements  MemberService{

    private final MemberMybatisMapperRepository memberMybatisRepository;

    public MemberServiceImpl(MemberMybatisMapperRepository memberMybatisRepository) {
        this.memberMybatisRepository = memberMybatisRepository;
    }


    @Override
    public int insertMember(MemberDTO memberDTO) {
        return memberMybatisRepository.insertMember(memberDTO);

    }
    @Override
    public MemberDTO login(MemberDTO memberDTO){
        return memberMybatisRepository.login(memberDTO);
    }

    @Override
    public boolean validCheckId(String loginId) {
        return memberMybatisRepository.validCheckId(loginId);
    }

    @Override
    public MemberDTO selectMemberDetail(Long id) {
        return memberMybatisRepository.selectMemberDetail(id);
    }

    @Override
    public int updateMember(MemberDTO memberDTO) {
        return memberMybatisRepository.updateMember(memberDTO);
    }

    @Override
    public int deleteMember(Long id) {
        return memberMybatisRepository.deleteMember(id);
    }

    @Override
    public List<MemberDTO> MemberAllList() {

        return memberMybatisRepository.MemberAllList();
    }

    @Override
    public int selectMemberTotalCount() {

        return memberMybatisRepository.selectMemberTotalCount();
    }

    @Override
    public boolean checkIdAndPw(MemberDTO memberDTO) {
        return memberMybatisRepository.checkIdAndPw(memberDTO);
    }

}
