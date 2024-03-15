package com.JYProject.project.model;

import lombok.Data;

import java.util.Date;
@Data
public class Reply {
    private int boardNo ; //댓글단 보드
    private int replyNo;  // 댓글 번호
    private int  writer;  // 댓글작성자
    private Date regDate; // 댓글 작성일자
    private Date updateDate; // 댓글 수정일자
    private Date deletedDate; // 댓글 삭제일자
    private int likes; //댓글 좋아요
    private int dislikes; //댓글 싫어요
}
