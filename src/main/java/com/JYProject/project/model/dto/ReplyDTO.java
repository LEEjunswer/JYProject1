package com.JYProject.project.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO  {
    //("댓글PK")
    private Long replyId;
    //("게시판 ID")
    private Long boardId ;
    //("댓글작성자")
    private Long memberId;
    //("댓글내용")
    private String content;
    //("등록일자")
    private LocalDateTime regDate;
    //("삭제일자")
    private LocalDateTime deleteDate;
    //("좋아요")
    private Long likes;
    //("싫어요")
    private Long dislikes;

}
