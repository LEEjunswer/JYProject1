package com.JYProject.project.repository.EventApplicantRepository;

import com.JYProject.project.model.EventApplicant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventApplicantMapperRepository {
    int insertApplicant(EventApplicant eventApplicant);
    EventApplicant findEventIdAndMemberId(EventApplicant eventApplicant);
    /* 밑에는 당첨자*/
    void updateWinningRandom(@Param("eventId") Long eventId, @Param("numWinners") int numWinners);
    /*밑에는 당첨후 안된사람들 모두 false*/
    void resultFailWinning(Long eventId);
    /*밑에는 이벤트 당첨자 리스트 가져오기*/
    List<EventApplicant> resultCheckWinner(Long eventId);
    void winningPointReward(Long eventId);
    /* 응모자 가져오기*/
    int findByEventIdCount(Long eventId);
}
