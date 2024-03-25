package com.JYProject.project.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class FileDTO {

    private Long id;
    private Long boardNo;
    private String fileName;
    private LocalDateTime regDate;
}
