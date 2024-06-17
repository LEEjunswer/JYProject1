package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDTO {
    private List<BoardDTO> boardList;
    private List<FileDTO> fileWeekBestList;
    private int totalPages;
    private int currentPage;
    private int totalCount;
}