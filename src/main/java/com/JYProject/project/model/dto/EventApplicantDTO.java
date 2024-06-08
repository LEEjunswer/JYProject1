package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventApplicantDTO {
    /*PK*/
    private Long eventApplicantId;
    /*이벤트 외래키 중복응모 방지*/
    private Long eventId;
    /*멤버 외래키*/
    private Long memberId;
    /*당첨자인지아닌지 처음은 null no면 false 고 True 당첨자*/
    private boolean winning;
}
