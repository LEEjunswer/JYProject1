package com.JYProject.project.controller;

import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.EventDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.AdminService.AdminEventService;
import com.JYProject.project.service.EventApplicantService.EventApplicantService;
import com.JYProject.project.service.FilterService.FilterService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    private final AdminEventService adminEventService;
    private final AdminBoardService adminBoardService;
    private final EventApplicantService eventApplicantService;
    @GetMapping("/admin/adminBoardJoin")
    public String create(HttpSession session, Model model){
        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
        MemberDTO m =  memberService.selectMemberDetail(loginId);
        model.addAttribute("memberId",m.getMemberId());
        return "/admin/adminBoardForm";
    }

    /*공지사항 및 이벤트 만약에 보드작성시 시작*/
    @PostMapping("/admin/boardJoin")
    public String join(@ModelAttribute AdminBoardDTO adminBoardDTO,
                        @ModelAttribute EventDTO eventDTO){;
        /*이벤트 공지사항*/
        System.out.println("adminBoardDTO = " + adminBoardDTO);
        System.out.println("eventDTO = " + eventDTO);
        if(adminBoardDTO.getCategory() == 1){
            adminEventService.insertEvent(eventDTO,adminBoardDTO);
        }

        adminBoardService.insertNotice(adminBoardDTO);
        return "redirect:/";
    }
    /*이벤트중인거 가져오기*/
    @GetMapping("/admin/raffle")
    public String raffle(Model model){
          List<EventDTO> eventDTOList =  adminEventService.findDuringEvent();
        System.out.println("eventDTOList = " + eventDTOList);
          model.addAttribute("eventList", eventDTOList);
        return "/admin/eventRaffle";
    }
    //이벤트 추첨
    @PostMapping("/admin/raffle/{people}/{eventId}/{checkPoint}")
    public String raffleStart(@PathVariable("people")int people,@PathVariable("eventId")Long eventId,@PathVariable("checkPoint")boolean checkPoint){
        /*포인트이벤트 X */
        if(!checkPoint) {
            eventApplicantService.updateWinningRandom(eventId, people);
            eventApplicantService.resultFailWinning(eventId);
            return "redirect:/admin/eventRaffle";
        }
        /*포안트이벤트 O */
        eventApplicantService.updateWinningRandom(eventId, people);
        eventApplicantService.resultFailWinning(eventId);
        eventApplicantService.resultCheckWinner(eventId);
        return "redirect:/admin/eventRaffle";
    }
}
