package com.JYProject.project.model;

import lombok.Data;

import java.util.Date;

@Data
public class File {
    private int fileNo ; // 파일번혼
    private int boardNo ; //글제목
    private String fileName ; //파일이름
    private Date regDate;  //파일이름등록일자
}
