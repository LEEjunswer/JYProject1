package com.JYProject.project.service.jpa;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(MemberDTO memberDTO);

    boolean findByEmailExists(String emailId);

    boolean findByLoginIdExists(String loginId);

    Member findById(Long id);

    List<Member> findAll();

    void updateMemberInfo(Long id, MemberDTO memberDTO);

    void deleteById(Long id);
}
