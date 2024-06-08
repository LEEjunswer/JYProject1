package com.JYProject.project.service.EventApplicantService;

import com.JYProject.project.model.EventApplicant;
import com.JYProject.project.model.dto.EventApplicantDTO;
import com.JYProject.project.repository.EventApplicantRepository.EventApplicantMapperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventApplicantServiceImpl implements  EventApplicantService{
    private final EventApplicantMapperRepository eventApplicantMapperRepository;

    @Override
    public int insertApplicant(EventApplicantDTO eventApplicantDTO) {
        return eventApplicantMapperRepository.insertApplicant(convertToEntity(eventApplicantDTO));
    }

    @Override
    public EventApplicantDTO findEventIdAndMemberId(EventApplicantDTO eventApplicantDTO) {
      EventApplicant eventApplicant =  eventApplicantMapperRepository.findEventIdAndMemberId(convertToEntity(eventApplicantDTO));
        return convertToDTO(eventApplicant);
    }

    @Override
    public void updateWinningRandom(Long eventId, int numWinners) {
        eventApplicantMapperRepository.updateWinningRandom(eventId,numWinners);
    }

    @Override
    public void resultFailWinning(Long eventId) {
        eventApplicantMapperRepository.resultFailWinning(eventId);
    }

    @Override
    public List<EventApplicantDTO> resultCheckWinner(Long eventId) {
        return eventApplicantMapperRepository.resultCheckWinner(eventId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public void winningPointReward(Long eventId) {
        eventApplicantMapperRepository.winningPointReward(eventId);
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
