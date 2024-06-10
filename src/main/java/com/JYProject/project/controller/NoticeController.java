package com.JYProject.project.controller;

import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.model.dto.EventDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.AdminService.AdminEventService;
import com.JYProject.project.service.EventApplicantService.EventApplicantService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

    private final AdminBoardService adminBoardService;
    private final AdminEventService adminEventService;
    private final EventApplicantService eventApplicantService;
    private final MemberService memberService;

    @GetMapping("/notice/noticeBoard/{category}")
    public String noticeBoard(@PathVariable("category")String category, Model model){
        if(category == null){
            List<AdminBoardDTO> noticeList =  adminBoardService.getAllNoticeList();
            model.addAttribute("noticeList",noticeList);
            return "/notice/noticeList";
        }
        int changeCategory= Integer.parseInt(category);
        List<AdminBoardDTO> noticeList = adminBoardService.getCategoryList(changeCategory);
        model.addAttribute("noticeList",noticeList);
        return "/notice/noticeList";
    }


    @GetMapping("/notice/content/{id}")
    public String noticeContent(@PathVariable("id")Long id, Model model, HttpSession session){
         String userId  = (String) session.getAttribute(SessionConst.USER_ID);

        /*이벤트 체크여부 이벤트가 있을시 같이 넘겨주고 응모를 할수 있게만든다*/
        if(adminEventService.findAdminBoardId(id) != null) {
        EventDTO eventDTO = adminEventService.findAdminBoardId(id);
        if (userId != null) {
            MemberDTO memberDTO= memberService.selectMemberDetail(userId);
            if (eventApplicantService.findEventIdAndMemberId(eventDTO.getEventId(), memberDTO.getMemberId()) != null) {
                model.addAttribute("applied", "applied");
            }
            model.addAttribute("myPoint",memberDTO.getPoint());
            }
            model.addAttribute("event", eventDTO);


    }
        AdminBoardDTO adminBoardDTO=  adminBoardService.getOneNoticeDetail(id);
        model.addAttribute("notice", adminBoardDTO);
        return "/notice/content";
    }
}
