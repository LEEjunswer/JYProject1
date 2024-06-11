package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private Long boardId;
    private Long memberId;
    private int questionPoint;
    private LocalDateTime regDate;
    private LocalDateTime  adoptionDate;
    private LocalDateTime deleteDate;
}
