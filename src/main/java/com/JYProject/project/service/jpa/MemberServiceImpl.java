package com.JYProject.project.service.jpa;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.jpa.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long join(MemberDTO memberDTO) {
        Member member = memberDTO.builder();
        memberRepository.join(member);
        return member.getId();
    }

    @Override
    public boolean findByEmailExists(String emailId) {
        // email이 있으면 true
        return memberRepository.findByEmailExists(emailId);
    }

    @Override
    public boolean findByLoginIdExists(String loginId) {
        // loginId가 있으면 true
        return memberRepository.findByLoginIdExists(loginId);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public void updateMemberInfo(Long id, MemberDTO memberDTO) {
        Member member = findById(id);
        if(!ObjectUtils.isEmpty(member)){
            member.updateMemberInfo(memberDTO);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Member member = findById(id);
        if(!ObjectUtils.isEmpty(member)){
            memberRepository.deleteById(id);
        }
    }

}
