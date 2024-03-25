package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
public class Filter {

    private Long id;
    private String word;
    private LocalDateTime createAt;
}
