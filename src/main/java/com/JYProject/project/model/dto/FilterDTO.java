package com.JYProject.project.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class FilterDTO {

    private Long id;
    private String word;
    private LocalDateTime createAt;
}
