package com.JYProject.project.repository.QuestionRepository;

import com.JYProject.project.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionMapperRepository {
    int insertQuestion(Question question);

    /*미채택 질문게시글 가져오기*/
    List<Question> findByNotAdoptedListOrderByDesc(Map<String, Object> params);
    Question getOneDetail(Long questionId);
    /*채택된 질문게시글 가져오기*/
    List<Question> findByAdoptListOrderByDesc(Map<String, Object> params);
    /*채택 미채택 상관없이 다 가져오기 페이징으로*/
    List<Question> findByAllListOrderByDesc(Map<String,Object> params);
    int questionChoose(Long questionId);
}
