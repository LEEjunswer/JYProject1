package com.JYProject.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class Filter {
    private int filterNo;   // 필터번호
    private String word;   // 필터할단어
    private Date createAt; // 등록일자 
}
