package com.JYProject.project.controller;

import com.JYProject.project.model.dto.*;
import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.AdminService.AdminEventService;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.EventApplicantService.EventApplicantService;
import com.JYProject.project.service.FilterService.FilterService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.ReplyService.ReplyService;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    private final AdminEventService adminEventService;
    private final AdminBoardService adminBoardService;
    private final EventApplicantService eventApplicantService;
    private final FilterService filterService;
    private final BoardService boardService;
    private final ReplyService replyService;
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
                        @ModelAttribute EventDTO eventDTO){
        /*이벤트 공지사항*/
        if(adminBoardDTO.getCategory() == 1){
            adminEventService.insertEvent(eventDTO,adminBoardDTO);
            return "redirect:/";
        }

        adminBoardService.insertNotice(adminBoardDTO);
        return "redirect:/";
    }
    /*이벤트중인거 가져오기*/
    @GetMapping("/admin/raffle")
    public String raffle(Model model){
          List<EventDTO> eventDTOList =  adminEventService.findDuringEvent();
          List<Integer>eventApplicantDTO = eventApplicantService.getApplicantCountsForEvents(eventDTOList);
        System.out.println("eventApplicantDTO = " + eventApplicantDTO);
          model.addAttribute("eventApplicantCount",eventApplicantDTO);
          model.addAttribute("eventList",eventDTOList);
        return "/admin/eventRaffle";
    }
    @GetMapping("/admin/filter")
    public String filter(Model model){
    List<FilterDTO> filterList= filterService.getAllFilter();

            model.addAttribute("filters",filterList);
            return "/admin/filter";
        }
    //이벤트 추첨
    @PostMapping("/admin/raffle/{people}/{eventId}")
    public String raffleStart(@PathVariable("people")int people,@PathVariable("eventId")Long eventId){
       EventDTO eventDTO= adminEventService.findByEventId(eventId);
        /*밑에는 나중에 한꺼번에 줄여보자 위랑*/
       /*포인트이벤트 X */
        if(!eventDTO.isEventPoint()) {
            eventApplicantService.updateWinningRandom(eventId, people);
            eventApplicantService.resultFailWinning(eventId);
                return "redirect:/admin/raffle";
        }
       /*포안트이벤트 O */
        /*밑에는 나중에 한꺼번에 줄여보자 위랑*/
        eventApplicantService.updateWinningRandom(eventId, people);
        eventApplicantService.resultFailWinning(eventId);
        eventApplicantService.resultCheckWinner(eventId);
        eventApplicantService.winningPointReward(eventId);
        return "redirect:/admin/raffle";
    }
    @GetMapping("/admin/memberList")
    public String getMemberList(Model model,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int pageSize ){
        List<MemberDTO> memberList =memberService.getAllMemberListPaging(page,pageSize);
        List<Integer> boardCounts = boardService.getUsersBoardCount(memberList);
        List<Integer> replyCounts = replyService.getUsersReplyCount(memberList);
        model.addAttribute("replyCount", replyCounts);
        model.addAttribute("boardCount",boardCounts);
        model.addAttribute("member",memberList);
        return "/admin/memberList";
    }
}
