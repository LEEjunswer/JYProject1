package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberMapperRepositoryImpl implements MemberMapperRepository {
    private final MemberMapperRepository memberMapperRepository;

    @Autowired
    public MemberMapperRepositoryImpl(MemberMapperRepository memberMapperRepository){
        this.memberMapperRepository = memberMapperRepository;
    }

    public int insertMember(Member member) {
        return memberMapperRepository.insertMember(member);
    }

    public Member login(Member member){
        return memberMapperRepository.login(member);

    }
    public Member selectMemberDetail(String loginId){

        return memberMapperRepository.selectMemberDetail(loginId);
    }

    public int updateMember(MemberDTO memberDTO){

        return memberMapperRepository.updateMember(memberDTO);
    }

    public boolean validCheckId(String loginId){

        return memberMapperRepository.validCheckId(loginId);
    }
    public int deleteMember(Long id){

        return memberMapperRepository.deleteMember(id);
    }

    public List<Member> MemberAllList(){

        return memberMapperRepository.MemberAllList();
    }

    public int selectMemberTotalCount(){

        return memberMapperRepository.selectMemberTotalCount();
    }

    @Override
    public boolean checkIdAndPw(Member member) {

        return memberMapperRepository.checkIdAndPw(member);
    }


}