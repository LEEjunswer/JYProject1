package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    
    @Comment("카테고리번호")
    private Long categoryId;
    @Comment("카테고리이름 1.자유 2.정보 3.추천 4.후기 나중에 보고 더 추가할 예정")
    private String categoryName;

    @Comment("등록일자")
    private LocalDateTime regDate;

}
