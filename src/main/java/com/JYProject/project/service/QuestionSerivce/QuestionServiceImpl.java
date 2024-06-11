package com.JYProject.project.service.QuestionSerivce;

import com.JYProject.project.model.Question;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.QuestionDTO;
import com.JYProject.project.repository.QuestionRepository.QuestionMapperRepository;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapperRepository questionMapperRepository;
    private final BoardService boardService;
    private final MemberService memberService;
    @Override
    public int insertQuestion(int questionPont, BoardDTO boardDTO, List<String> fileUrls) throws Exception {
       MemberDTO memberDTO = memberService.getOneMemberId(boardDTO.getMemberId());
        if(memberDTO.getPoint() < questionPont){
           return 0;
       }
         memberDTO.setPoint(questionPont);
        QuestionDTO questionDTO =  new QuestionDTO();
      Long boardId = boardService.insertBoard(boardDTO,fileUrls);
        System.out.println("boardId = " + boardId);
        questionDTO.setQuestionPoint(questionPont);
        questionDTO.setBoardId(boardId);
        questionDTO.setMemberId(boardDTO.getMemberId());
        memberService.addApplicantEvent(memberDTO);
        return  questionMapperRepository.insertQuestion(convertToEntity(questionDTO));
    }

    private QuestionDTO convertToDTO(Question question){
        QuestionDTO questionDTO =new QuestionDTO();
        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setBoardId(question.getBoardId());
        questionDTO.setMemberId(question.getMemberId());
        questionDTO.setRegDate(question.getRegDate());
        questionDTO.setAdoptionDate(question.getAdoptionDate());
        questionDTO.setDeleteDate(question.getDeleteDate());
        questionDTO.setQuestionPoint(question.getQuestionPoint());
        return questionDTO;
    }
    private Question convertToEntity(QuestionDTO questionDTO){
        Question question = new Question();
        question.setQuestionId(questionDTO.getQuestionId());
        question.setMemberId(questionDTO.getMemberId());
        question.setBoardId(questionDTO.getBoardId());
        question.setAdoptionDate(questionDTO.getAdoptionDate());
        question.setRegDate(questionDTO.getRegDate());
        question.setDeleteDate(questionDTO.getDeleteDate());
        question.setQuestionPoint(questionDTO.getQuestionPoint());
        return  question;
    }
}
