package com.JYProject.project.repository.QuestionRepository;

import com.JYProject.project.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapperRepository {
    int insertQuestion(Question question);
}
