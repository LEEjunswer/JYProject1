package com.JYProject.project.service.MemberService;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.MemberRepository.MemberMapperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements  MemberService{

    private final MemberMapperRepository memberMybatisRepository;

    
    // 프로필 이미지 저장 장소 //이것도 파일 Service로 옮겨야하나 좀 더 공부를 해봐야겟다.
    @Value("${profile.upload.path}")
    private String profileUploadPath;


    @Override
    public int insertMember(MemberDTO memberDTO) {
        Member member = convertToEntity(memberDTO);

        return memberMybatisRepository.insertMember(member);

    }

    @Override
    public MemberDTO getOneMemberId(Long id) {
        return convertToDTO(memberMybatisRepository.getOneMemberId(id));
    }


    @Override
    public MemberDTO login(MemberDTO memberDTO){
     Member checkLogin =   memberMybatisRepository.login(convertToEntity(memberDTO));

        System.out.println("checkLogin = " + checkLogin);
        if(checkLogin == null){
            return  null;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate lastLoginDate = checkLogin.getLoginId() != null ? checkLogin.getLastLoginDate().toLocalDate() : null;
        if (lastLoginDate == null || !lastLoginDate.equals(today)) {

            int additionalPoints = 10; // 일단 로그인 시 10 포인트 증가
            MemberDTO memberDTO1 = new MemberDTO();
            memberDTO1.setPoint(additionalPoints);
            memberDTO1.setMemberId(memberDTO.getMemberId());
            memberMybatisRepository.addLoginPoint(convertToEntity(memberDTO1));
        }
        memberMybatisRepository.updateLastLogin(checkLogin.getMemberId(), now);
        return convertToDTO(checkLogin);
    }



    @Override
    public boolean validCheckId(String loginId) {
        return memberMybatisRepository.validCheckId(loginId);
    }

    @Override
    public boolean validCheckNick(String nickname) {
        return memberMybatisRepository.validCheckNick(nickname);
    }

    @Override
    public boolean validCheckEmail(String email) {
        return memberMybatisRepository.validCheckEmail(email);
    }

    @Override
    public MemberDTO selectMemberDetail(String loginId) {
            Member  member= memberMybatisRepository.selectMemberDetail(loginId);

        return convertToDTO(member);
    }

    @Override
    public int changePassword(Long memberId,String password) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberId);
        memberDTO.setPw(password);
        return memberMybatisRepository.updateMember(convertToEntity(memberDTO));
    }

    @Override
    public int changeNickname(Long memberId, String nickname) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberId);
        memberDTO.setNickname(nickname);
        return memberMybatisRepository.updateMember(convertToEntity(memberDTO));
    }

    @Override
    public int deleteMember(MemberDTO memberDTO) {

        return memberMybatisRepository.deleteMember(convertToEntity(memberDTO));
    }

    @Override
    public int addPointLikes(Long id) {
        return memberMybatisRepository.addPointLikes(id);
    }

    @Override
    public int addPointDisLikes(Long id) {
        return memberMybatisRepository.addPointDisLikes(id);
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
    public int getChooseAdopt(MemberDTO memberDTO) {
        return memberMybatisRepository.getChooseAdopt(convertToEntity(memberDTO));
    }

    @Override
    public boolean checkIdAndPw(MemberDTO memberDTO) {
        Member member   = convertToEntity(memberDTO);
        return memberMybatisRepository.checkIdAndPw(member);
    }

    @Override
    public void updateProfileImage(String loginId,MultipartFile profileImage)throws  IOException  {
        String filename = System.currentTimeMillis() + "-" + profileImage.getOriginalFilename();
        Path uploadDirectory = Paths.get(profileUploadPath);
        String profileImg = profileUploadPath + "/"+ filename;
        if (!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }
        Path filePath = uploadDirectory.resolve(filename);
        Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        memberMybatisRepository.updateProfileImg(loginId, profileImg);
    }

    @Override
    public int addApplicantEvent(MemberDTO memberDTO) {
        return memberMybatisRepository.addApplicantEvent(convertToEntity(memberDTO));
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
        member.setPoint(memberDTO.getPoint());
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
        memberDTO.setPoint(member.getPoint());
        return memberDTO;
    }
}
