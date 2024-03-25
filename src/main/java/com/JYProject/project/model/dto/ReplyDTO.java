package com.JYProject.project.model.dto;

import com.JYProject.project.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
public class ReplyDTO extends BaseEntity {

    private Long id;
    private Long boardNo ;
    private Long writer;
    private LocalDateTime deletedDate;
    private Long likes;
    private Long dislikes;


}
