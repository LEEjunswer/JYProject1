package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface MemberMybatisRepository {

    public int insertMember(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);
    public MemberDTO selectMemberDetail(Long id);

    public int updateMember(MemberDTO memberDTO);

    public boolean validCheckId(String loginId);
    public int deleteMember(Long id);

    public List<MemberDTO> selectMemberList();

    public int selectMemberTotalCount();



}
