package com.JYProject.project.controller;



import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.BoardResponseDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.model.dto.ReplyResponseDTO;
import com.JYProject.project.service.BoardServiceImpl;
import com.JYProject.project.service.ReplyServiceImpl;
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

    private final ReplyServiceImpl replyService;

    @GetMapping("/reply/myReplyList/{memberId}")
    public String myReplyList(@PathVariable("memberId")Long memberId, Model model){
        List<ReplyDTO>  replyList = replyService.getMyReplyList(memberId);
        System.out.println("replyList = " + replyList);
        List<BoardDTO> boardList = replyService.getBoardsFromReplies(memberId);
        System.out.println("boardList = " + boardList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("myReply",replyList);
        return "/reply/myReplyList";
    }

}
