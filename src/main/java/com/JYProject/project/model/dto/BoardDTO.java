package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    @Comment("게시판번호")
    private Long boardId;

    @Comment("작성자")
    private String writer;


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

    @Comment("글작성자 id")
    private String memberId;
    @Comment("조회수")
    private int viewCnt;

    @Comment("좋아요")
    private Long likes;

    @Comment("싫어요")
    private Long dislikes;

    @Comment("카레고리번호")
    private int categoryId;
}