package com.JYProject.project.service;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.repository.mybatis.ReplyMapperRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapperRepositoryImpl replyMybatisRepository;



    @Override
    public int insertReply(ReplyDTO replyDTO) {
       Reply reply = convertToEntity(replyDTO);

        return  replyMybatisRepository.insertReply(reply );
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
    public int deleteReply(Long id) {
        return replyMybatisRepository.deleteReply(id);
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

    private Reply convertToEntity(ReplyDTO replyDTO){
        Reply reply = new Reply();
        reply.setReplyId(replyDTO.getReplyId());
        reply.setContent(replyDTO.getContent());
        reply.setMemberId(replyDTO.getMemberId());
        reply.setBoardId(replyDTO.getBoardId());
        reply.setLikes(replyDTO.getLikes());
        reply.setRegDate(replyDTO.getRegDate());
        reply.setDeletedDate(replyDTO.getDeletedDate());
        reply.setDislikes(replyDTO.getDislikes());
        return  reply;
    }
    private ReplyDTO convertToDTO(Reply reply){
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyId(reply.getReplyId());
        replyDTO.setMemberId(replyDTO.getMemberId());
        replyDTO.setDislikes(reply.getDislikes());
        replyDTO.setContent(reply.getContent());
        replyDTO.setLikes(reply.getLikes());
        replyDTO.setBoardId(reply.getBoardId());
        replyDTO.setRegDate(reply.getRegDate());
        replyDTO.setDeletedDate(reply.getDeletedDate());
        return replyDTO;


    }
}
