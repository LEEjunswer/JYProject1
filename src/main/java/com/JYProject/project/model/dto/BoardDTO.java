package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDTO {

    private Long id;
    private String name;
    private String writer;
    private String title;
    private String content;
    private int viewCnt;
    private Long likes;
    private Long dislikes;
}