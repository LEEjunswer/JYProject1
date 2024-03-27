package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyMybatisMapperRepository implements ReplyMapperRepository {

    private final ReplyMapperRepository replyMapperRepository;
    @Autowired
    public ReplyMybatisMapperRepository(ReplyMapperRepository replyMapperRepository){
       this.replyMapperRepository = replyMapperRepository;
    }
    @Override
    public int insertReply(ReplyDTO replyDTO) {
        return replyMapperRepository.insertReply(replyDTO);
    }

    @Override
    public ReplyDTO selectOneReply(Long id) {
        return replyMapperRepository.selectOneReply(id);
    }

    @Override
    public int updateReply(ReplyDTO replyDTO) {
        return replyMapperRepository.updateReply(replyDTO);
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
