package com.JYProject.project.service.EventApplicantService;

import com.JYProject.project.model.EventApplicant;
import com.JYProject.project.model.dto.EventApplicantDTO;
import com.JYProject.project.model.dto.EventDTO;
import com.JYProject.project.model.dto.MemberDTO;

import java.util.List;


public interface EventApplicantService {
    int insertApplicant(MemberDTO memberDTO, EventDTO eventDTO);
    EventApplicantDTO findEventIdAndMemberId(Long eventId ,Long memberId);
    /* 밑에는 당첨자*/
    void updateWinningRandom(Long eventId, int numWinners);
    void resultFailWinning(Long eventId);
    /*밑에는 이벤트 당첨자 리스트 가져오기*/
    List<EventApplicantDTO> resultCheckWinner(Long eventId);
    void winningPointReward(Long eventId);

    int findByEventIdCount(Long eventId);
    List<Integer> getApplicantCountsForEvents(List<EventDTO> events);

}
