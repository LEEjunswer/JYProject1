package com.JYProject.project.service;

import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.repository.mybatis.ReplyMybatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplySerivce {

    private final ReplyMybatisRepository replyMybatisRepository;

    @Autowired
    public  ReplyServiceImpl(ReplyMybatisRepository replyMybatisRepository){
        this.replyMybatisRepository=replyMybatisRepository;
    }

    @Override
    public int insertReply(ReplyDTO replyDTO) {
        return replyMybatisRepository.insertReply(replyDTO);
    }

    @Override
    public ReplyDTO selectOneReply(Long id) {
        return replyMybatisRepository.selectOneReply(id);
    }

    @Override
    public int updateReply(ReplyDTO replyDTO) {
        return replyMybatisRepository.updateReply(replyDTO);
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
}
