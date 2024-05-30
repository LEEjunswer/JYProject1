package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyMapperRepository {

     int insertReply(Reply reply);
    Reply selectOneReply(Long id);
    int updateReply(Reply reply);
    int deleteReply(Long id);
    int replyLikesTotalCount(Long id);
    int replyDisLikesTotalCount(Long id);
    int getOneBoardReplyCount(Long boardId);
    List<Reply> getOneBoardReply(Map<String, Object> params);
}
