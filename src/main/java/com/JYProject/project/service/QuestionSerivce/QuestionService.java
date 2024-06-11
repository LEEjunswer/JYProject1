package com.JYProject.project.service.QuestionSerivce;

import com.JYProject.project.model.Question;
import com.JYProject.project.model.dto.BoardDTO;

import java.util.List;

public interface QuestionService {

    int insertQuestion(int questionPont, BoardDTO boardDTO, List<String> fileUrls) throws Exception;
}
