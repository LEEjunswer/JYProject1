package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adopter {
    private Long adopterId;
    private Long questionId;
    private Long memberId;
    private Long replyId;
    private int adoptionPoint;
    private LocalDateTime regDate;
    private LocalDateTime deleteDate;
}
