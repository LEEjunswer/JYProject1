package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberMybatisRepository implements MemberRepository {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberMybatisRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public int insertMember(MemberDTO memberDTO) {
        return memberRepository.insertMember(memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO){
        return memberRepository.login(memberDTO);

    }
    public MemberDTO selectMemberDetail(Long id){

        return memberRepository.selectMemberDetail(id);
    }

    public int updateMember(MemberDTO memberDTO){

        return memberRepository.updateMember(memberDTO);
    }

    public boolean validCheckId(String loginId){

        return memberRepository.validCheckId(loginId);
    }
    public int deleteMember(Long id){

        return memberRepository.deleteMember(id);
    }

    public List<MemberDTO> MemberAllList(){

        return memberRepository.MemberAllList();
    }

    public int selectMemberTotalCount(){

        return memberRepository.selectMemberTotalCount();
    }

    @Override
    public boolean checkIdAndPw(MemberDTO memberDTO) {

        return memberRepository.checkIdAndPw(memberDTO);
    }


}
