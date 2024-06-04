package com.JYProject.project.controller;



import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.ReplyDTO;

import com.JYProject.project.service.ReplyService.ReplyService;
import com.JYProject.project.service.ReplyService.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/myReplyList/{memberId}")
    public String myReplyList(@PathVariable("memberId")Long memberId, Model model){
        List<ReplyDTO>  replyList = replyService.getMyReplyList(memberId);
        List<BoardDTO> boardList = replyService.getBoardsFromReplies(memberId);
        model.addAttribute("boardList", boardList);
        model.addAttribute("myReply",replyList);
        return "/reply/myReplyList";
    }

}
