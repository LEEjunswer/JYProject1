package com.JYProject.project.service.EventApplicantService;

import com.JYProject.project.model.EventApplicant;
import com.JYProject.project.model.dto.EventApplicantDTO;
import com.JYProject.project.model.dto.EventDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.EventApplicantRepository.EventApplicantMapperRepository;
import com.JYProject.project.service.AdminService.AdminEventService;
import com.JYProject.project.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventApplicantServiceImpl implements  EventApplicantService{
    private final EventApplicantMapperRepository eventApplicantMapperRepository;
    private final AdminEventService eventService;
    private final MemberService memberService;
    @Override
    public int insertApplicant(MemberDTO memberDTO, EventDTO eventDTO) {
        EventApplicantDTO eventApplicantDTO = new EventApplicantDTO();
        eventApplicantDTO.setMemberId(memberDTO.getMemberId());
        eventApplicantDTO.setEventId(eventDTO.getEventId());
        EventApplicant eventApplicant =  eventApplicantMapperRepository.findEventIdAndMemberId(convertToEntity(eventApplicantDTO));
        if (eventApplicant == null) {
            memberDTO.setPoint(eventDTO.getPoint());
            memberService.addApplicantEvent(memberDTO);
            return eventApplicantMapperRepository.insertApplicant(convertToEntity(eventApplicantDTO));
        }

        return 0;
    }

    @Override
    public EventApplicantDTO findEventIdAndMemberId(Long eventId ,Long memberId) {
        EventApplicantDTO eventApplicantDTO = new EventApplicantDTO();
        eventApplicantDTO.setMemberId(memberId);
        eventApplicantDTO.setEventId(eventId);
      EventApplicant eventApplicant =  eventApplicantMapperRepository.findEventIdAndMemberId(convertToEntity(eventApplicantDTO));
        if (eventApplicant == null) {
            return null;
        }

        return convertToDTO(eventApplicant);
    }

    @Override
    public void updateWinningRandom(Long eventId, int numWinners) {
        eventApplicantMapperRepository.updateWinningRandom(eventId,numWinners);

    }


    @Override
    public void resultFailWinning(Long eventId) {
        eventApplicantMapperRepository.resultFailWinning(eventId);
       /*밑에는 종료 이벤트 위닝 정하고 나머지는 전부 펄스로 준다*/
        eventService.updateEventEnd(eventId);
    }

    @Override
    public List<EventApplicantDTO> resultCheckWinner(Long eventId) {
        return eventApplicantMapperRepository.resultCheckWinner(eventId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public void winningPointReward(Long eventId) {
        eventApplicantMapperRepository.winningPointReward(eventId);
    }

    @Override
    public int findByEventIdCount(Long eventId) {
        return eventApplicantMapperRepository.findByEventIdCount(eventId);
    }

    @Override
    public List<Integer> getApplicantCountsForEvents(List<EventDTO> events) {
        return  events.stream()
                .map(event -> findByEventIdCount(event.getEventId()))
                .collect(Collectors.toList());
    }



    private EventApplicant convertToEntity(EventApplicantDTO eventApplicantDTO){
        EventApplicant eventApplicant = new EventApplicant();
        eventApplicant.setEventApplicantId(eventApplicantDTO.getEventApplicantId());
        eventApplicant.setEventId(eventApplicantDTO.getEventId());
        eventApplicant.setMemberId(eventApplicantDTO.getMemberId());
        return  eventApplicant;
    }
    private  EventApplicantDTO convertToDTO(EventApplicant eventApplicant){
        EventApplicantDTO eventApplicantDTO = new EventApplicantDTO();
        eventApplicantDTO.setEventApplicantId(eventApplicant.getEventApplicantId());
        eventApplicantDTO.setEventId(eventApplicantDTO.getEventId());
        eventApplicantDTO.setMemberId(eventApplicantDTO.getMemberId());
        return eventApplicantDTO;
    }
}
