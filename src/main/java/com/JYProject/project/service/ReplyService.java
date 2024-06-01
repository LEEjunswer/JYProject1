package com.JYProject.project.service;

import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.model.dto.ReplyResponseDTO;

import java.util.List;
import java.util.Map;

public interface ReplyService {

     int insertReply(ReplyDTO replyDTO);
     ReplyDTO selectOneReply(Long id);
     int updateReply(ReplyDTO replyDTO);
     int deleteReply(Long replyId);
     int replyLikesTotalCount(Long id);
     int replyDisLikesTotalCount(Long id);
     int getOneBoardReplyCount(Long boardId);
     ReplyResponseDTO getOneBoardReplyPaging(Long boardId, int page, int size);
}
