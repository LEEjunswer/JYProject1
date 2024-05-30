package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    //("좋아요Id")
    private Long likeId;

    //("Member_id")
    private Long memberId;

    //("Board_id")
    private Long boardId;

    //("is_likes false= 싫어요 true=좋아요")
    private boolean likes;

    //("좋아요 누른 날짜")
    private String regDate;
}
