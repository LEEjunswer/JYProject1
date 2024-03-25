package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
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
