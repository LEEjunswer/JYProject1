package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    @Comment("게시판번호")
    private Long id;

    @Comment("게시판의 이름")
    private String name;

    @Comment("작성자")
    private String writer;


    @Comment("글제목")
    private String title;


    @Comment("글내용")
    private String content;

    @Comment("조회수")
    private int viewCnt;

    @Comment("좋아요")
    private Long likes;


    @Comment("싫어요")
    private Long dislikes;
}