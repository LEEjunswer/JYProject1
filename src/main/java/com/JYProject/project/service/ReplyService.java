package com.JYProject.project.service;

import com.JYProject.project.model.dto.ReplyDTO;

public interface ReplyService {

     int insertReply(ReplyDTO replyDTO);
     ReplyDTO selectOneReply(Long id);
     int updateReply(ReplyDTO replyDTO);
     int deleteReply(Long id);
     int replyLikesTotalCount(Long id);
     int replyDisLikesTotalCount(Long id);
     int getOneBoardReplyCount(Long boardId);
}
