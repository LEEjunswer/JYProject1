package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdopterDTO {
    private Long adopterId;
    private Long questionId;
    private Long replyId;
    private Long memberId;
    private int adoptionPoint;
    private LocalDateTime regDate;
    private LocalDateTime deleteDate;
}
