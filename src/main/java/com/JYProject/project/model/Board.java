package com.JYProject.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int boardNo;  //게시글번호
    private String  writer;  //작성자
    private String title;  // 글제목
    private String content; // 글내용
    private Date   regdata; // 글등록날짜
    private Date  updateDate; // 글 수정날짜
    private Date  deleteDate; // 글 삭제날짜 
    private int likes; // 좋아요
    private  int dislikes; //싫어요
}
