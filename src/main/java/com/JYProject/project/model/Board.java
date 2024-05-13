package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Comment("게시판번호")
    private Long boardId;

    @Comment("글작성자 id")
    private Long memberId;
    @Comment("카레고리번호")
    private Long categoryId;

    @Comment("글작성자")
    private String writer;
    @Comment("작성자파일")
    private Long fileId;

    @Comment("글제목")
    private String title;

    @Comment("등록일자")
    private LocalDateTime regDate;

    @Comment("수정일자")
    private LocalDateTime updateDate;

    @Comment("삭제일자 처음에는 Null")
    private LocalDateTime deletedDate;

    @Comment("글내용")
    private String content;

    @Comment("조회수")
    private int viewCnt;


    @Comment("좋아요")
    private int likes;

    @Comment("싫어요")
    private int dislikes;

}