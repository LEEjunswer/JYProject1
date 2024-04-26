package com.JYProject.project.service;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.repository.mybatis.ReplyMapperRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapperRepositoryImpl replyMybatisRepository;

    @Autowired
    public  ReplyServiceImpl(ReplyMapperRepositoryImpl replyMybatisRepository){
        this.replyMybatisRepository=replyMybatisRepository;
    }

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

    private Reply convertToEntity(ReplyDTO replyDTO){
        Reply reply = new Reply();
        reply.setId(replyDTO.getId());
        reply.setContent(replyDTO.getContent());
        reply.setWriter(replyDTO.getWriter());
        reply.setBoardId(replyDTO.getBoardId());
        reply.setLikes(replyDTO.getLikes());
        reply.setRegDate(replyDTO.getRegDate());
        reply.setDeletedDate(replyDTO.getDeletedDate());
        reply.setDislikes(replyDTO.getDislikes());
        return  reply;
    }
    private ReplyDTO convertToDTO(Reply reply){
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setId(reply.getId());
        replyDTO.setWriter(replyDTO.getWriter());
        replyDTO.setDislikes(reply.getDislikes());
        replyDTO.setContent(reply.getContent());
        replyDTO.setLikes(reply.getLikes());
        replyDTO.setBoardId(reply.getBoardId());
        replyDTO.setRegDate(reply.getRegDate());
        replyDTO.setDeletedDate(reply.getDeletedDate());
        return replyDTO;


    }
}
