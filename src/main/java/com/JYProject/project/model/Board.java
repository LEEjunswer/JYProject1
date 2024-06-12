package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    //("게시판번호")
    private Long boardId;
    //("글작성자 id")
    private Long memberId;
    //("카레고리번호")
    private Long categoryId;
    //("작성자파일")
    private Long fileId;
    //("글제목")
    private String title;
    //("등록일자")
    private LocalDateTime regDate;
    //("수정일자")
    private LocalDateTime updateDate;
    //("삭제일자 처음에는 Null")
    private LocalDateTime deleteDate;
    //("글내용")
    private String content;
    //("조회수")
    private int viewCnt;
    //("좋아요")
    private int likes;
    //("싫어요")
    private int dislikes;
    /*멤버 쿼리 조인할 때 필요하다*/
    private Member memberInfo;
    private Category categoryInfo;
}