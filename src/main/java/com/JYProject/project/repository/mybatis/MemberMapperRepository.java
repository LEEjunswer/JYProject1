package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberMapperRepository {

    public int insertMember(Member member);

    Member login(Member member);
    public Member selectMemberDetail(String loginId);

    public int updateMember(MemberDTO memberDTO);

    public boolean validCheckId(String loginId);
    public int deleteMember(Long id);

    public List<Member> MemberAllList();

    public int selectMemberTotalCount();
    public boolean checkIdAndPw(Member member);


}
