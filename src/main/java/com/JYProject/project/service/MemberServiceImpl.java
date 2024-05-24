package com.JYProject.project.service;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.mybatis.MemberMapperRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements  MemberService{

    private final MemberMapperRepositoryImpl memberMybatisRepository;

    
    // 프로필 이미지 저장 장소
    @Value("${profile.upload.path}")
    private String profileUploadPath;

    public MemberServiceImpl(MemberMapperRepositoryImpl memberMybatisRepository) {
        this.memberMybatisRepository = memberMybatisRepository;
    }


    @Override
    public int insertMember(MemberDTO memberDTO) {
        Member member = convertToEntity(memberDTO);

        return memberMybatisRepository.insertMember(member);

    }
    @Override
    public MemberDTO login(MemberDTO memberDTO){

     Member checkLogin =   memberMybatisRepository.login(convertToEntity(memberDTO));
        if(checkLogin == null){
            return  null;
        }
        return convertToDTO(checkLogin);
    }

    @Override
    public boolean validCheckId(String loginId) {
        return memberMybatisRepository.validCheckId(loginId);
    }

    @Override
    public MemberDTO selectMemberDetail(String loginId) {
            Member  member= memberMybatisRepository.selectMemberDetail(loginId);

        return convertToDTO(member);
    }

    @Override
    public int updateMember(MemberDTO memberDTO) {
        return memberMybatisRepository.updateMember(memberDTO);
    }

    @Override
    public int deleteMember(Long id) {
        return memberMybatisRepository.deleteMember(id);
    }

    @Override
    public List<MemberDTO> MemberAllList() {
            List<Member> memberList = memberMybatisRepository.MemberAllList();
        return memberList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public int selectMemberTotalCount() {

        return memberMybatisRepository.selectMemberTotalCount();
    }

    @Override
    public boolean checkIdAndPw(MemberDTO memberDTO) {
        Member member   = convertToEntity(memberDTO);
        return memberMybatisRepository.checkIdAndPw(member);
    }

    @Override
    public void updateProfileImage(String loginId,MultipartFile profileImage, String profile)throws  IOException  {

        Path uploadDirectory = Paths.get(profileUploadPath);
        String profileImg = profileUploadPath + "/"+ profile;
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }
        Path filePath = uploadDirectory.resolve(profile);
        Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        memberMybatisRepository.updateProfileImg(loginId, profileImg);
    }



    private Member convertToEntity(MemberDTO memberDTO){
        Member member = new Member();
        member.setMemberId(memberDTO.getMemberId());
        member.setLoginId(memberDTO.getLoginId());
        member.setPw(memberDTO.getPw());
        member.setName(memberDTO.getName());
        member.setActive(memberDTO.getActive());
        member.setEmail(memberDTO.getEmail());
        member.setProfileImg(memberDTO.getProfileImg());
        member.setLastLoginDate(memberDTO.getLastLoginDate());
        member.setNickname(memberDTO.getNickname());
        member.setRegDate(memberDTO.getRegDate());
        member.setAddressDetail(memberDTO.getAddressDetail());
        member.setPhone(memberDTO.getPhone());
        member.setGrade(memberDTO.getGrade());
        return  member;
    }
    private MemberDTO convertToDTO(Member member){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setLoginId(member.getLoginId());
        memberDTO.setPw(member.getPw());
        memberDTO.setActive(member.getActive());
        memberDTO.setName(member.getName());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setLastLoginDate(member.getLastLoginDate());
        memberDTO.setProfileImg(member.getProfileImg());
        memberDTO.setNickname(member.getNickname());
        memberDTO.setRegDate(member.getRegDate());
        memberDTO.setAddressDetail(member.getAddressDetail());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setGrade(member.getGrade());
        return memberDTO;
    }
}
