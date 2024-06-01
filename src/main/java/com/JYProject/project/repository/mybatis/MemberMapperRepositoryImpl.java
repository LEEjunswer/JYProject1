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

    @Override
    public Member getOneMemberId(Long id) {
        return memberMapperRepository.getOneMemberId(id);
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

    @Override
    public boolean validCheckNick(String nickname) {
        return memberMapperRepository.validCheckNick(nickname);
    }

    @Override
    public boolean validCheckEmail(String email) {
        return memberMapperRepository.validCheckEmail(email);
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

    // 프로필 이미지 넣기
    @Override
    public void updateProfileImg(String loginId, String profileImg) {
        memberMapperRepository.updateProfileImg(loginId,profileImg);
        return ;
    }


}
