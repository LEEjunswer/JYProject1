package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReplyMapperRepositoryImpl implements ReplyMapperRepository {

    private final ReplyMapperRepository replyMapperRepository;

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
    public int deleteReply(Long replyId) {
        return replyMapperRepository.deleteReply(replyId);
    }

    @Override
    public int replyLikesTotalCount(Long id) {
        return replyMapperRepository.replyLikesTotalCount(id);
    }

    @Override
    public int getMyReplyCount(Long memberId) {
        return replyMapperRepository.getMyReplyCount(memberId);
    }

    @Override
    public List<Reply> getMyReplyList(Long memberId) {
        return replyMapperRepository.getMyReplyList(memberId);
    }

    @Override
    public int replyDisLikesTotalCount(Long id) {
        return replyMapperRepository.replyDisLikesTotalCount(id);
    }

    @Override
    public int getOneBoardReplyCount(Long boardId) {
        return replyMapperRepository.getOneBoardReplyCount(boardId);
    }

    @Override
    public List<Reply> getOneBoardReply(Map<String, Object> params) {
        return replyMapperRepository.getOneBoardReply(params);
    }


}

