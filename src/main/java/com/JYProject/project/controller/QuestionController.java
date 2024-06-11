package com.JYProject.project.controller;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.service.QuestionSerivce.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

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

}
