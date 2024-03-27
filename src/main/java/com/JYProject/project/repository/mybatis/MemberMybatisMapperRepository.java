package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberMybatisMapperRepository implements MemberMapperRepository {
    private final MemberMapperRepository memberMapperRepository;

    @Autowired
    public MemberMybatisMapperRepository(MemberMapperRepository memberMapperRepository){
        this.memberMapperRepository = memberMapperRepository;
    }

    public int insertMember(MemberDTO memberDTO) {
        return memberMapperRepository.insertMember(memberDTO);
    }

    public MemberDTO login(MemberDTO memberDTO){
        return memberMapperRepository.login(memberDTO);

    }
    public MemberDTO selectMemberDetail(Long id){

        return memberMapperRepository.selectMemberDetail(id);
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

    public List<MemberDTO> MemberAllList(){

        return memberMapperRepository.MemberAllList();
    }

    public int selectMemberTotalCount(){

        return memberMapperRepository.selectMemberTotalCount();
    }

    @Override
    public boolean checkIdAndPw(MemberDTO memberDTO) {

        return memberMapperRepository.checkIdAndPw(memberDTO);
    }


}
