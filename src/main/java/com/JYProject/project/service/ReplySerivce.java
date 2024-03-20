package com.JYProject.project.service;

import com.JYProject.project.model.dto.ReplyDTO;

public interface ReplySerivce {

    public int insertReply(ReplyDTO replyDTO);
    public ReplyDTO selectOneReply(Long id);

    public int updateReply(ReplyDTO replyDTO);

    public int deleteReply(Long id);
    public int replyLikesTotalCount(Long id);
    public int replyDisLikesTotalCount(Long id);
}
