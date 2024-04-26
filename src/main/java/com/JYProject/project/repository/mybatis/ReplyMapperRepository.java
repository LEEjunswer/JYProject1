package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapperRepository {

    public int insertReply(Reply reply);
    public Reply selectOneReply(Long id);

    public int updateReply(Reply reply);

    public int deleteReply(Long id);
    public int replyLikesTotalCount(Long id);
    public int replyDisLikesTotalCount(Long id);
}
