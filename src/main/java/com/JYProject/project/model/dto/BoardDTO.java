package com.JYProject.project.model.dto;

import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

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
    private MemberDTO memberInfo;
}