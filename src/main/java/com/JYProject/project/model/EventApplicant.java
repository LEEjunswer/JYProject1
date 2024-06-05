package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventApplicant {
    
    /*PK*/
    private Long eventApplicantId;
    /*이벤트 외래키 중복응모 방지*/
    private Long eventId;
    /*멤버 외래키*/
    private Long memberId;
}
