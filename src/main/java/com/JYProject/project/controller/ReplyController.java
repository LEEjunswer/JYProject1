package com.JYProject.project.controller;


import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.service.ReplyServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyController {

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

}
