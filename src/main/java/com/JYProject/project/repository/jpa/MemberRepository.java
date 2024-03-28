package com.JYProject.project.repository.jpa;


import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Long join(Member member);

    boolean findByEmailExists(String emailId);

    boolean findByLoginIdExists(String loginId);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    void updateMemberInfo(MemberDTO memberDTO);

    void deleteById(Long id);
}
