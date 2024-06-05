package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminBoard {
    /*PK 밑에걸 좀줄일걸 ;;*/
  private Long adminBoardId;
  /*외래키 memberId는 admin*/
  private Long  memberId;
  /* 카테고리는 0 = 공지사항 1 = 이벤트*/
  private int  category;
  private String title;
  private String content;
  private int viewCnt;
  private LocalDateTime regDate;
  private LocalDateTime deleteDate;
  private LocalDateTime endDate;

}
