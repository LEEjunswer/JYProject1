package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {

    @Comment("좋아요Id")
    private Long likeId;

    @Comment("Member_id")
    private Long memberId;

    @Comment("Board_id")
    private Long boardId;

    @Comment("is_likes false= 싫어요 true=좋아요")
    private boolean likes;

    @Comment("좋아요 누른 날짜")
    private String regDate;
}
