package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberServiceImpl memberService;

    //프로필 이미지 경로
    @Value("${profile.upload.path}")
    private String profileUploadPath;


    @PostMapping(value = "/members/idValidCheck")
    public boolean idValidCheck(@RequestParam String loginId) {


        return  memberService.validCheckNick(loginId);
    }

    @PostMapping(value = "/members/nickValidCheck")
    public boolean nickValidCheck(@RequestParam String nickname) {

        return memberService.validCheckNick(nickname);
    }

    @PostMapping(value = "/members/updatePassword")
    public ResponseEntity<Map<String, String>> changePassword(@RequestParam String password,HttpSession session){
        Map<String, String> response = new HashMap<>();
       String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
        if(isLogin == null){
            response.putIfAbsent("message", "잘못된접급니다");
            return ResponseEntity.ok(response);
        }
        MemberDTO changePassword = new MemberDTO();
        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        changePassword.setMemberId(memberDTO.getMemberId());
        changePassword.setPw(password);
        memberService.updateMember(changePassword);
        response.putIfAbsent("message", memberDTO.getNickname()+" 비밀번호 변경이 정상적으로 되었습니다.");
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/members/updateNickname")
    public ResponseEntity<Map<String, String>> changeNickname(@RequestParam String nickname,HttpSession session){
        Map<String, String> response = new HashMap<>();
        String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
        if(isLogin == null){
            response.putIfAbsent("message", "잘못된접급니다");
            return ResponseEntity.ok(response);
        }
        MemberDTO changeNickName = new MemberDTO();
        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        changeNickName.setMemberId(memberDTO.getMemberId());
        changeNickName.setNickname(nickname);
        memberService.updateMember(changeNickName);
        session.setAttribute(SessionConst.USER_NAME, changeNickName.getNickname());
        response.putIfAbsent("message", "닉네임이 "+changeNickName.getNickname()+"이 정상적으로 변경되었습니다.");
        return ResponseEntity.ok(response);
    }

    // 이메일은 나중에 추가할 예정 인증받거나 할떄
/*    @PostMapping(value = "/members/nickValidCheck")
    public boolean emailValidCheck(@RequestParam String email) {

        boolean isValidCheck = memberService.validCheckEmail(email);
        return !isValidCheck;
    }*/

    // 나중에  밑에있는거 전부 SerivceImpl로 옮길예정
    @PostMapping(value = "members/updateProfile")
    public ResponseEntity<Map<String, Object>> updateProfileImage(@RequestParam("loginId") String loginId, @RequestParam("profileImage") MultipartFile profileImage , HttpSession session) throws IOException {
        if (profileImage.isEmpty()) {
            throw new IllegalArgumentException("Profile image file is empty");
        }

        // 파일 저장 로직
        String filename = System.currentTimeMillis() + "-" + profileImage.getOriginalFilename();
        memberService.updateProfileImage(loginId,profileImage,filename);

        MemberDTO memberDetail = memberService.selectMemberDetail(loginId);
        String profile = memberDetail.getProfileImg();
        String basePath = "C:/ecpliseworkspace/JYproject/ljy/src/main/resources";
        String relativePath = profile.replace(basePath, "");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "프로필 이미지 저장이 성공적으로 완료되었습니다.");
        session.removeAttribute("profile");
        session.setAttribute("profile", relativePath);
        return ResponseEntity.ok(response);
    }
}