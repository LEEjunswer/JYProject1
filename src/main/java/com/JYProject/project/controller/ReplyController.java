package com.JYProject.project.controller;


import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.service.ReplyServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class ReplyController {
    /*  나중에 apiController 옮기고 수정할 예정  ajax 로 전부 받을 예쩡*/
    private final ReplyServiceImpl replyService;

    @Autowired
    public ReplyController(ReplyServiceImpl replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/boards/content/{idx}/reply")
    public String create(@PathVariable("idx") Long idx, HttpSession session, @ModelAttribute ReplyDTO replyDTO) {
        MemberDTO m = (MemberDTO) session.getAttribute("log");
        Long writer = (Long) m.getId();
        replyDTO.setBoardNo(idx);
        replyDTO.setWriter(writer);
        int check = replyService.insertReply(replyDTO);
        return "redirect:/boards/content/" + idx;
    }
    public String create(@PathVariable("idx") Long idx, HttpSession session, @ModelAttribute ReplyDTO replyDTO, RedirectAttributes redirectAttributes){
        MemberDTO m = (MemberDTO)session.getAttribute("log");
        Long writer =  (Long)m.getId();
        if(writer == null ){
            redirectAttributes.addFlashAttribute("error","로그인상태 아님 잘못된 접근입니다");
            return "redirect:/members/home";
        }
        replyDTO.setBoardNo(idx);
        replyDTO.setWriter(writer);
        int check = replyService.insertReply(replyDTO);
        redirectAttributes.addFlashAttribute("suc", "댓글이 정상적으로 달렸습니다");
    return "redirect:/boards/content/" +idx;
    }

    @PostMapping("boards/content/{idx}/reply/{delReplyIdx}")
    public String delelteReply(@PathVariable("idx") Long idx, @PathVariable("delReplyIdx") Long replyIdx,HttpSession session){
        MemberDTO m = (MemberDTO)session.getAttribute("log");
        Long writer = (Long)m.getId();
        if(writer== null){
           return  "members/home";
        }
        replyService.deleteReply(replyIdx);

        return "redirect:boards/content"+idx;
    }
}
