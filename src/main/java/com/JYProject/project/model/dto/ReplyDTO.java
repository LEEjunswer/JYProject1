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
public class ReplyDTO extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("댓글PK")
    @Column(name="reply_id")
    private Long id;

    @Column
    @Comment("게시판")
    private Long boardNo ;

    @Column
    @Comment("댓글작성자")
    private Long writer;

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
