package com.JYProject.project.model.dto;

import com.JYProject.project.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO  {

    @Comment("댓글PK")
    private Long id;


    @Comment("게시판")
    private Long boardNo ;

    @Comment("댓글작성자")
    private Long writer;


    @Comment("등록일자")
    private LocalDateTime regDate;


    @Comment("삭제일자")
    private LocalDateTime deletedDate;

    @Comment("좋아요")
    private Long likes;

    @Comment("싫어요")
    private Long dislikes;


}
