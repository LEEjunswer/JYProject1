package com.JYProject.project.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {


    //("필터번호")
    private Long id;

    //("필터할단어")
    private String word;

    //("등록일자")
    private LocalDateTime createAt;
}
