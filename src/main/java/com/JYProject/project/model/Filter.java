package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    //("필터번호")
    private Long filterId;

    //("필터할단어")
    private String word;
    
    //("등록일자")
    private LocalDateTime createAt;
}
