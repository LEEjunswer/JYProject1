package com.JYProject.project.model.dto;

import lombok.*;


import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {


    //("카테고리번호")
    private Long categoryId;
    //("카테고리이름 1.자유 2.정보 3.추천 4.후기 나중에 보고 더 추가할 예정")
    private String categoryName;

    //("등록일자")
    private LocalDateTime regDate;

}
