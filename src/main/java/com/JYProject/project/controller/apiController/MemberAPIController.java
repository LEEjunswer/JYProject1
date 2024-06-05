package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.MemberService.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;
    
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

        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        memberService.changePassword(memberDTO.getMemberId(), password);
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
        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        memberService.changeNickname(memberDTO.getMemberId(),nickname);
        session.setAttribute(SessionConst.USER_NAME, nickname);
        response.putIfAbsent("message", "닉네임이 "+nickname +"이 정상적으로 변경되었습니다.");
        return ResponseEntity.ok(response);
    }

    // 이메일은 나중에 추가할 예정 인증받거나 할떄
/*    @PostMapping(value = "/members/nickValidCheck")
    public boolean emailValidCheck(@RequestParam String email) {

        boolean isValidCheck = memberService.validCheckEmail(email);
        return !isValidCheck;
    }*/
    @PostMapping(value = "members/updateProfile")
    public ResponseEntity<Map<String, Object>> updateProfileImage(@RequestParam("loginId") String loginId, @RequestParam("profileImage") MultipartFile profileImage , HttpSession session) throws IOException {
        if (profileImage.isEmpty()) {
            throw new IllegalArgumentException("Profile image file is empty");
        }

        memberService.updateProfileImage(loginId,profileImage);
        MemberDTO memberDetail = memberService.selectMemberDetail(loginId);
        String profile = memberDetail.getProfileImg();
        String basePath = "C:/ecpliseworkspace/JYproject/ljy/src/main/resources";
        String relativePath = profile.replace(basePath, "");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "프로필 이미지 저장이 성공적으로 완료되었습니다.");
        session.setAttribute("profile", relativePath);
        return ResponseEntity.ok(response);
    }
}