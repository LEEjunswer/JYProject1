package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.EventDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.AdminService.AdminEventService;
import com.JYProject.project.service.EventApplicantService.EventApplicantService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NoticeAPIController {
    private final EventApplicantService eventApplicantService;
    private final AdminBoardService adminBoardService;
    private final AdminEventService adminEventService;
    private final MemberService memberService;

    @PostMapping("/notice/applyRaffle")
    public ResponseEntity<Map<String,String>> applyEvent(HttpSession httpSession, @RequestBody EventDTO eventDTO){
        Map<String, String> response = new HashMap<>();
        String userId = (String) httpSession.getAttribute(SessionConst.USER_ID);
        MemberDTO memberDTO  = memberService.selectMemberDetail(userId);
        EventDTO eventDetail= adminEventService.findByEventId(eventDTO.getEventId());
        if (eventDTO.getPoint() <= memberDTO.getPoint()) {
            response.put("message","정상적으로 응모가 완료되었습니다.");
            eventApplicantService.insertApplicant(memberDTO,eventDetail);
            return ResponseEntity.ok(response);
        }
        response.put("message","잘못된 접근입니다. 나중에 다시 시도해주세요");
        return ResponseEntity.ok(response);
    }
}
