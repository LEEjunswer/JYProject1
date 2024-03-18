package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("게시글번호")
    private Long id;

    @Column
    @Comment("작성자")
    private String  writer;

    @Column
    @Comment("글제목")
    private String title;

    @Column
    @Comment("글내용")
    private String content;

    @Column
    @Comment("글등록날짜")
    private LocalDateTime regData;

    @Column
    @Comment("수정날짜")
    private LocalDateTime  updateDate;

    @Column
    @Comment("삭제날짜")
    private LocalDateTime  deleteDate;

    @Column
    @Comment("좋아요")
    private Long likes;

    @Column
    @Comment("싫어요")
    private Long dislikes;
}
