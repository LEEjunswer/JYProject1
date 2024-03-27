package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberMapperRepository {

    public int insertMember(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);
    public MemberDTO selectMemberDetail(Long id);

    public int updateMember(MemberDTO memberDTO);

    public boolean validCheckId(String loginId);
    public int deleteMember(Long id);

    public List<MemberDTO> MemberAllList();

    public int selectMemberTotalCount();
    public boolean checkIdAndPw(MemberDTO memberDTO);


}
