package com.JYProject.project.model.dto;

import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {

    @Comment("필터번호")
    private Long id;

    @Comment("필터할단어")
    private String word;
    
    @Comment("등록일자")
    private LocalDateTime createAt;
}
