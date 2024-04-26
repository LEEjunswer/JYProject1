package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    @Comment("필터번호")
    private Long id;

    @Comment("필터할단어")
    private String word;
    
    @Comment("등록일자")
    private LocalDateTime createAt;
}
