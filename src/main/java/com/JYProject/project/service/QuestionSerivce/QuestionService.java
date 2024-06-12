package com.JYProject.project.service.QuestionSerivce;

import com.JYProject.project.model.Question;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.QuestionDTO;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    int insertQuestion(int questionPont, BoardDTO boardDTO, List<String> fileUrls) throws Exception;


    QuestionDTO getOneDetail(Long questionId);
    /*미채택 질무게시글 가져오기 */
    List<QuestionDTO> findByNotAdoptedListOrderByDesc(int page, int size);
    /*채택된 질문게시글 가져오기*/
    List<QuestionDTO> findByAdoptListOrderByDesc(int page, int size);
    /*채택 미채택 상관없이 다 가져오기 페이징으로*/
    List<QuestionDTO> findByAllListOrderByDesc(int page, int size);
    int questionChoose(Long questionId);
}
