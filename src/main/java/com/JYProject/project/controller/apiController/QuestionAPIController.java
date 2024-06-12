package com.JYProject.project.controller.apiController;

import com.JYProject.project.service.QuestionSerivce.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionAPIController {
        private final QuestionService questionService;
}
