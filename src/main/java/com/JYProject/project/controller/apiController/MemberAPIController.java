package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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


    @RequestMapping(value = "members/idValidCheck", method = RequestMethod.GET)
    public String idValidCheck(@RequestParam String loginId) {
        MemberDTO getOneId = memberService.selectMemberDetail(loginId);
        if (getOneId == null) {
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "members/nickValidCheck", method = RequestMethod.GET)
    public String nickValidCheck(@RequestParam String nickname, @RequestParam String loginId) {

        return "true";
    }

    @RequestMapping(value = "members/updateProfile", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> updateProfileImage(@RequestParam("loginId") String loginId, @RequestParam("profileImage") MultipartFile profileImage) throws IOException {
        if (profileImage.isEmpty()) {
            throw new IllegalArgumentException("Profile image file is empty");
        }

        // 파일 저장 로직
        String filename = System.currentTimeMillis() + "-" + profileImage.getOriginalFilename();
        memberService.updateProfileImage(loginId,profileImage,filename);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "프로필 이미지 저장이 성공적으로 완료되었습니다.");
        return ResponseEntity.ok(response);
    }
}