package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private Long eventId;
    /*이벤트 게시글시 외래키를 받는단*/
    private Long adminBoardId;
    /*이벤트 내용 상풍같은거*/
    private String eventTitle;
    /*이벤트 포인트  입력시 그만큼 포인트를 차감하고 응모를 한다*/
    private int point;
    /*이벤트 시작일자*/
    private LocalDateTime startDate;
    /*이벤트 종료일자*/
    private LocalDateTime endDate;

    private boolean lotteryCompleted;
}
