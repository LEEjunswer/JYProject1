package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    
    @Comment("카테고리번호")
    private Long id;
    @Comment("카테고리이름 1.자유 2.정보 3.추천 4.후기 나중에 보고 더 추가할 예정")
    private String categoryName;

    @Comment("등록일자")
    private LocalDateTime regDate;

}
