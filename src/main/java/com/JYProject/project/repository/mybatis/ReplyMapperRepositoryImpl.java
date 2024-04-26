package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyMapperRepositoryImpl implements ReplyMapperRepository {

    private final ReplyMapperRepository replyMapperRepository;
    @Autowired
    public ReplyMapperRepositoryImpl(ReplyMapperRepository replyMapperRepository){
       this.replyMapperRepository = replyMapperRepository;
    }
    @Override
    public int insertReply(Reply reply) {
        return replyMapperRepository.insertReply(reply);
    }

    @Override
    public Reply selectOneReply(Long id) {
        return replyMapperRepository.selectOneReply(id);
    }

    @Override
    public int updateReply(Reply reply) {
        return replyMapperRepository.updateReply(reply);
    }

    @Override
    public int deleteReply(Long id) {
        return replyMapperRepository.deleteReply(id);
    }

    @Override
    public int replyLikesTotalCount(Long id) {
        return replyMapperRepository.replyLikesTotalCount(id);
    }

    @Override
    public int replyDisLikesTotalCount(Long id) {
        return replyMapperRepository.replyDisLikesTotalCount(id);
    }


}

