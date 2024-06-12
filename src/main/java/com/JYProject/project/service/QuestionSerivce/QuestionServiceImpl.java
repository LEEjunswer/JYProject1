package com.JYProject.project.service.QuestionSerivce;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.Member;
import com.JYProject.project.model.Question;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.QuestionDTO;
import com.JYProject.project.repository.QuestionRepository.QuestionMapperRepository;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        questionDTO.setQuestionPoint(questionPont);
        questionDTO.setBoardId(boardId);
        questionDTO.setMemberId(boardDTO.getMemberId());
        memberService.addApplicantEvent(memberDTO);
        return  questionMapperRepository.insertQuestion(convertToEntity(questionDTO));
    }

    @Override
    public QuestionDTO getOneDetail(Long questionId) {
        return convertToDTO(questionMapperRepository.getOneDetail(questionId));
    }

    @Override
    public List<QuestionDTO> findByNotAdoptedListOrderByDesc(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        int offset = (page - 1);
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", size);
        return questionMapperRepository.findByNotAdoptedListOrderByDesc(params).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDTO> findByAdoptListOrderByDesc(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        int offset = (page - 1);
        Map<String, Object> params = new HashMap<>();

        params.put("offset", offset);
        params.put("pageSize", size);
        return questionMapperRepository.findByAdoptListOrderByDesc(params).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDTO> findByAllListOrderByDesc(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        int offset = (page - 1);
        Map<String, Object> params = new HashMap<>();

        params.put("offset", offset);
        params.put("pageSize", size);
        return questionMapperRepository.findByAllListOrderByDesc(params).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public int questionChoose(Long questionId) {
        return questionMapperRepository.questionChoose(questionId);
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
        questionDTO.setBoardInfo(convertBoardToDTO(question.getBoardInfo()));
        questionDTO.setMemberInfo(convertToMemberDTO(question.getMemberInfo()));
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
        question.setBoardInfo(convertToBoardEntity(questionDTO.getBoardInfo()));
        question.setMemberInfo(convertToMemberEntity(questionDTO.getMemberInfo()));
        return  question;
    }


    private Board convertToBoardEntity(BoardDTO boardDTO) {
        if(boardDTO == null){
            return  null;
        }
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setBoardId(boardDTO.getBoardId());
        board.setRegDate(boardDTO.getRegDate());
        board.setDeleteDate(boardDTO.getDeleteDate());
        board.setCategoryId(boardDTO.getCategoryId());
        board.setDislikes(boardDTO.getDislikes());
        board.setLikes(boardDTO.getLikes());
        board.setFileId(boardDTO.getFileId());
        board.setViewCnt(boardDTO.getViewCnt());
        board.setMemberId(boardDTO.getMemberId());
        board.setMemberInfo(convertToMemberEntity(boardDTO.getMemberInfo()));
        return board;
    }

    private BoardDTO convertBoardToDTO(Board board) {
        if(board == null){
            return  null;
        }
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setDeleteDate(board.getDeleteDate());
        boardDTO.setRegDate(board.getRegDate());
        boardDTO.setBoardId(board.getBoardId());
        boardDTO.setLikes(board.getLikes());
        boardDTO.setDislikes(board.getDislikes());
        boardDTO.setCategoryId(board.getCategoryId());
        boardDTO.setMemberId(board.getMemberId());
        boardDTO.setViewCnt(board.getViewCnt());
        boardDTO.setFileId(board.getFileId());
        boardDTO.setMemberInfo(convertToMemberDTO(board.getMemberInfo()));
        return boardDTO;
    }
    private Member convertToMemberEntity(MemberDTO memberDTO){
        if(memberDTO == null){
            return null;
        }
        Member member = new Member();
        member.setMemberId(memberDTO.getMemberId());
/*        member.setLoginId(memberDTO.getLoginId());
        member.setPw(memberDTO.getPw());
        member.setName(memberDTO.getName());
        member.setActive(memberDTO.getActive());
        member.setEmail(memberDTO.getEmail());
        member.setProfileImg(memberDTO.getProfileImg());
        member.setLastLoginDate(memberDTO.getLastLoginDate());*/
        member.setNickname(memberDTO.getNickname());
        /*member.setRegDate(memberDTO.getRegDate());
        member.setAddressDetail(memberDTO.getAddressDetail());
        member.setPhone(memberDTO.getPhone());
        member.setGrade(memberDTO.getGrade());
        member.setPoint(memberDTO.getPoint());*/
        return  member;
    }
    private MemberDTO convertToMemberDTO(Member member){
        if(member == null){
            return  null;
        }
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(member.getMemberId());
   /*     memberDTO.setLoginId(member.getLoginId());
        memberDTO.setPw(member.getPw());
        memberDTO.setActive(member.getActive());
        memberDTO.setName(member.getName());
        memberDTO.setEmail(member.getEmail());
        memberDTO.setLastLoginDate(member.getLastLoginDate());
        memberDTO.setProfileImg(member.getProfileImg());*/
        memberDTO.setNickname(member.getNickname());
      /*  memberDTO.setRegDate(member.getRegDate());
        memberDTO.setAddressDetail(member.getAddressDetail());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setGrade(member.getGrade());
        memberDTO.setPoint(member.getPoint());*/
        return memberDTO;
    }
}
