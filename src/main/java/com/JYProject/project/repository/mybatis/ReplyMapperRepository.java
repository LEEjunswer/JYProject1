package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapperRepository {

     int insertReply(Reply reply);
    Reply selectOneReply(Long id);
    int updateReply(Reply reply);
    int deleteReply(Long id);
    int replyLikesTotalCount(Long id);
    int replyDisLikesTotalCount(Long id);
    int getOneBoardReplyCount(Long id);
}
