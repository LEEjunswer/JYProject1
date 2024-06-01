package com.JYProject.project.service;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.model.dto.ReplyResponseDTO;
import com.JYProject.project.repository.mybatis.ReplyMapperRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapperRepositoryImpl replyMybatisRepository;
    private  final MemberServiceImpl memberService;


    @Override
    public int insertReply(ReplyDTO replyDTO) {
       Reply reply = convertToEntity(replyDTO);

        return  replyMybatisRepository.insertReply(reply);
    }

    @Override
    public ReplyDTO selectOneReply(Long id){
    Reply reply  = replyMybatisRepository.selectOneReply(id);
        return convertToDTO(reply);
    }

    @Override
    public int updateReply(ReplyDTO replyDTO) {
   Reply reply = convertToEntity(replyDTO);
        return replyMybatisRepository.updateReply(reply);
    }

    @Override
    public int deleteReply(Long replyId) {
        System.out.println("replyId 1층 = " + replyId);
        int  check=replyMybatisRepository.deleteReply(replyId);
        
        return check;
    }

    @Override
    public int replyLikesTotalCount(Long id) {
        return replyMybatisRepository.replyLikesTotalCount(id);
    }

    @Override
    public int replyDisLikesTotalCount(Long id) {
        return replyMybatisRepository.replyDisLikesTotalCount(id);
    }

    @Override
    public int getOneBoardReplyCount(Long boardId) {
        return replyMybatisRepository.getOneBoardReplyCount(boardId);
    }

    @Override
    public ReplyResponseDTO getOneBoardReplyPaging(Long boardId, int page, int size) {
        if (page < 1) {
            page = 1;
        }
        int offset = (page - 1);
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", boardId);
        params.put("offset", offset);
        params.put("pageSize", size);

        List<ReplyDTO> replyList = replyMybatisRepository.getOneBoardReply(params).stream().map(this::convertToDTO).collect(Collectors.toList());
        System.out.println("replyList = " + replyList);
        int totalRecords = replyMybatisRepository.getOneBoardReplyCount(boardId);
        int totalPages = (int) Math.ceil((double) totalRecords / size);
        List<MemberDTO> memberList = new ArrayList<>();
        ReplyResponseDTO responseList = new ReplyResponseDTO();
        for(ReplyDTO reply : replyList){
            MemberDTO   memberDTO=memberService.getOneMemberId(reply.getMemberId());
            memberList.add(memberDTO);
        }
        responseList.setMemberList(memberList);
        responseList.setReplyList(replyList);
        responseList.setTotalPages(totalPages);
        responseList.setCurrentPage(page);
        responseList.setTotalCount(totalRecords);

        return responseList;
    }

    private Reply convertToEntity(ReplyDTO replyDTO){
        Reply reply = new Reply();
        reply.setReplyId(replyDTO.getReplyId());
        reply.setContent(replyDTO.getContent());
        reply.setMemberId(replyDTO.getMemberId());
        reply.setBoardId(replyDTO.getBoardId());
        reply.setRegDate(replyDTO.getRegDate());
        reply.setDeleteDate(replyDTO.getDeleteDate());
/*        *//*        reply.setLikes(replyDTO.getLikes());*//*
        reply.setDislikes(replyDTO.getDislikes());*/
        return  reply;
    }
    private ReplyDTO convertToDTO(Reply reply){
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyId(reply.getReplyId());
        replyDTO.setBoardId(reply.getBoardId());
        replyDTO.setMemberId(reply.getMemberId());
        replyDTO.setContent(reply.getContent());
        replyDTO.setRegDate(reply.getRegDate());
        replyDTO.setDeleteDate(reply.getDeleteDate());
/*        replyDTO.setDislikes(reply.getDislikes());
        replyDTO.setLikes(reply.getLikes());*/
        return replyDTO;


    }
}
