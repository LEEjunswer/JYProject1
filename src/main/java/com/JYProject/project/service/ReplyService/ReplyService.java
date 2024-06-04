package com.JYProject.project.service.ReplyService;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.model.dto.ReplyResponseDTO;

import java.util.List;

public interface ReplyService {

     int insertReply(ReplyDTO replyDTO);
     ReplyDTO selectOneReply(Long id);
     int updateReply(ReplyDTO replyDTO);
     int deleteReply(Long replyId);
     int replyLikesTotalCount(Long id);
     List<ReplyDTO> getMyReplyList(Long memberId);
     void replyDeleteFindMemberId(Long memberId);
     int getMyReplyCount(Long memberId);
     int replyDisLikesTotalCount(Long id);
     int getOneBoardReplyCount(Long boardId);
     List<BoardDTO> getBoardsFromReplies(Long memberId);
     ReplyResponseDTO getOneBoardReplyPaging(Long boardId, int page, int size);
}
