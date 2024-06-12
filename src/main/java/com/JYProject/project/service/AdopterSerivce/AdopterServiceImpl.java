package com.JYProject.project.service.AdopterSerivce;

import com.JYProject.project.model.Adopter;
import com.JYProject.project.model.dto.AdopterDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.QuestionDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.repository.AdopterRepository.AdopterMapperRepository;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.QuestionSerivce.QuestionService;
import com.JYProject.project.service.ReplyService.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdopterServiceImpl implements AdopterService {
        private final AdopterMapperRepository adopterMapperRepository;
        private final MemberService memberService;
        private final ReplyService replyService;
        private final QuestionService questionService;
    @Override
    public int insertAdopt(Long replyId, Long questionId) {
        System.out.println("진입체크");
        ReplyDTO replyDTO = replyService.selectOneReply(replyId);
        System.out.println("replyDTO = " + replyDTO);
        QuestionDTO questionDTO = questionService.getOneDetail(questionId);
        AdopterDTO adopterDTO = new AdopterDTO();
        adopterDTO.setQuestionId(questionId);
        adopterDTO.setAdoptionPoint(questionDTO.getQuestionPoint());
        adopterDTO.setReplyId(replyDTO.getReplyId());
        adopterDTO.setMemberId(replyDTO.getMemberId());
        System.out.println("adopterDTO = " + adopterDTO);
        int check=adopterMapperRepository.insertAdopt(convertToEntity(adopterDTO));
        if(check == 0){
            return 0;
        }
        questionService.questionChoose(questionId);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(replyDTO.getMemberId());
        memberDTO.setPoint(questionDTO.getQuestionPoint());
        System.out.println("memberDTO = " + memberDTO);
        memberService.getChooseAdopt(memberDTO);
        return check;
    }
    private Adopter convertToEntity(AdopterDTO adopterDTO){
        Adopter adopter = new Adopter();
        adopter.setAdopterId(adopterDTO.getAdopterId());
        adopter.setQuestionId(adopterDTO.getQuestionId());
        adopter.setAdoptionPoint(adopterDTO.getAdoptionPoint());
        adopter.setMemberId(adopterDTO.getMemberId());
        adopter.setRegDate(adopterDTO.getRegDate());
        adopter.setDeleteDate(adopterDTO.getDeleteDate());
        adopter.setReplyId(adopterDTO.getReplyId());
        return adopter;
    }
    private AdopterDTO convertToDTO(Adopter adopter){
        AdopterDTO adopterDTO = new AdopterDTO();
        adopterDTO.setAdopterId(adopter.getAdopterId());
        adopterDTO.setMemberId(adopter.getMemberId());
        adopterDTO.setQuestionId(adopter.getQuestionId());
        adopterDTO.setAdoptionPoint(adopter.getAdoptionPoint());
        adopterDTO.setReplyId(adopter.getReplyId());
        adopterDTO.setRegDate(adopter.getRegDate());
        adopterDTO.setDeleteDate(adopter.getDeleteDate());
        return adopterDTO;
    }
}
