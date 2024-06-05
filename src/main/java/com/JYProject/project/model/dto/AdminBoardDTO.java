package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminBoardDTO {
    /*PK*/
    private Long adminBoardId;
    /*외래키 memberId는 admin*/
    private Long  memberId;
    /* 카테고리는 0 = 공지사항 1 = 이벤트*/
    private int  category;
    private String title;
    private String content;
    private int viewCnt;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;

}
