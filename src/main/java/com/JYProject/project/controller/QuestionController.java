package com.JYProject.project.controller;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.QuestionDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.model.dto.ReplyResponseDTO;
import com.JYProject.project.service.QuestionSerivce.QuestionService;
import com.JYProject.project.service.ReplyService.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final ReplyService replyService;
    @PostMapping("/question/join")
    public String create(
            @ModelAttribute BoardDTO boardDTO,
            @RequestParam(value = "fileUrls") List<String> fileUrls,
            @RequestParam(value = "questionPoint") int questionPoint,
            RedirectAttributes redirectAttributes) {
        try {
                int getQuestionId = questionService.insertQuestion(questionPoint,boardDTO, fileUrls);
                redirectAttributes.addFlashAttribute("suc", "성공적으로 게시글 등록되었습니다");

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "게시글 등록에 실패했습니다.");
        }
        return "redirect:/boards/list";
    }
    @GetMapping("/question/list")
    public String questionList(Model model
    , @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int pageSize){
            List<QuestionDTO> questionList =questionService.findByNotAdoptedListOrderByDesc(page,pageSize);
            model.addAttribute("list",questionList);
        return "/question/list";
    }
    /*밑은 미채택 채택상관없이 보여주는건 0이고 1은 미채택  채택일경우 2이다*/
    @GetMapping("/question/list/{check}")
    public String isAdoptList(@PathVariable("check")int check, Model model,@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int pageSize){
        if(check == 0){
                List<QuestionDTO> questionList = questionService.findByAllListOrderByDesc(page,pageSize);
                model.addAttribute("list",questionList);
            return "/question/list";
        }else if(check == 1){
            List<QuestionDTO> questionList = questionService.findByNotAdoptedListOrderByDesc(page,pageSize);
            model.addAttribute("list",questionList);
            return  "/question/list";
        }
        List<QuestionDTO> questionList = questionService.findByAdoptListOrderByDesc(page,pageSize);
        model.addAttribute("list",questionList);
        return  "/question/list";
    }
    @GetMapping("/question/content/{id}")
    public String questionDetail(@PathVariable("id") Long id,Model model){
            QuestionDTO vo = questionService.getOneDetail(id);
            ReplyResponseDTO replies = replyService.getOneBoardReplyPaging(vo.getBoardId(),1,10);
        model.addAttribute("repliesMember", replies.getMemberList());
        model.addAttribute("replies",replies);
            model.addAttribute("vo", vo);
            return "/question/content";
    }
}
