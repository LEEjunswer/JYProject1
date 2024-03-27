package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapperRepository {

    public int insertReply(ReplyDTO replyDTO);
    public ReplyDTO selectOneReply(Long id);

    public int updateReply(ReplyDTO replyDTO);

    public int deleteReply(Long id);
    public int replyLikesTotalCount(Long id);
    public int replyDisLikesTotalCount(Long id);
}
