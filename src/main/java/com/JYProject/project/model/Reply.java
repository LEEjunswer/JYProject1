package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply  {
    @Comment("댓글PK")
    private Long id;
    @Comment("게시판 ID")
    private Long boardId ;
    @Comment("댓글작성자")
    private Long writer;
    @Comment("댓글내용")
    private String content;
    @Comment("등록일자")
    private LocalDateTime regDate;
    @Comment("삭제일자")
    private LocalDateTime deletedDate;
    @Comment("좋아요")
    private Long likes;
    @Comment("싫어요")
    private Long dislikes;

}
