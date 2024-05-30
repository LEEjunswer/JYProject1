package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply  {
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
    private LocalDateTime deletedDate;
// 밑에는 나중에 구현 예쩡
    /*    //("좋아요")
    private Long likes;
    //("싫어요")
    private Long dislikes;*/

}
