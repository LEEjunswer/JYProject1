package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("댓글PK")
    private Long id;
    @Column
    @Comment("게시판")
    private Long boardNo ;
    @Column
    @Comment("댓글작성자")
    private Long writer;
    @Column
    @Comment("작성일자")
    private LocalDateTime regDate;
    @Column
    @Comment("수정일자")
    private LocalDateTime updateDate;
    @Column
    @Comment("삭제일자")
    private LocalDateTime deletedDate;
    @Column
    @Comment("좋아요")
    private Long likes;
    @Column
    @Comment("싫어요")
    private Long dislikes;
}
