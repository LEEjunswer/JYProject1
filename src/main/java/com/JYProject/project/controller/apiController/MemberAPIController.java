package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.MemberServiceImpl;
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