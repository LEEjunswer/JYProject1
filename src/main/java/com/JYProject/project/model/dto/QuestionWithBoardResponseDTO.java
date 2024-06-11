package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionWithBoardResponseDTO {
    private BoardDTO boardDTO;
    private QuestionDTO questionDTO;
}
