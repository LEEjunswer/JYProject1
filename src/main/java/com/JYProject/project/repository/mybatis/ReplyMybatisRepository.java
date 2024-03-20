package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyMybatisRepository implements ReplyRepository {

    private final ReplyRepository replyRepository;
    @Autowired
    public ReplyMybatisRepository(ReplyRepository replyRepository){
       this.replyRepository = replyRepository;
    }
    @Override
    public int insertReply(ReplyDTO replyDTO) {
        return replyRepository.insertReply(replyDTO);
    }

    @Override
    public ReplyDTO selectOneReply(Long id) {
        return replyRepository.selectOneReply(id);
    }

    @Override
    public int updateReply(ReplyDTO replyDTO) {
        return replyRepository.updateReply(replyDTO);
    }

    @Override
    public int deleteReply(Long id) {
        return replyRepository.deleteReply(id);
    }

    @Override
    public int replyLikesTotalCount(Long id) {
        return replyRepository.replyLikesTotalCount(id);
    }

    @Override
    public int replyDisLikesTotalCount(Long id) {
        return replyRepository.replyDisLikesTotalCount(id);
    }
}
