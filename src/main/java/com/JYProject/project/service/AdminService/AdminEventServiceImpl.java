package com.JYProject.project.service.AdminService;

import com.JYProject.project.model.Event;
import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.model.dto.EventDTO;
import com.JYProject.project.repository.AdminRepository.AdminEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminEventServiceImpl implements AdminEventService {

    private  final AdminEventRepository adminEventRepository;
    private  final AdminBoardService adminBoardService;

    @Override
    public int insertEvent(EventDTO eventDTO, AdminBoardDTO adminBoardDTO) {
        Long adminBoardId= adminBoardService.insertNotice(adminBoardDTO);
        eventDTO.setAdminBoardId(adminBoardId);
        return adminEventRepository.insertEvent(conventToEntity(eventDTO));
    }

    @Override
    public EventDTO findAdminBoardId(Long adminBoardId) {
     EventDTO eventDTO = convertToDTO(adminEventRepository.findAdminBoardId(adminBoardId));
        System.out.println("eventDTO = " + eventDTO);
     return eventDTO;
    }

    @Override
    public List<EventDTO> findDuringEvent() {
        return adminEventRepository.findDuringEvent().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public int updateEventEnd(Long eventId) {
        return adminEventRepository.updateEventEnd(eventId);
    }

    @Override
    public EventDTO findByEventId(Long eventId) {
        return convertToDTO(adminEventRepository.findByEventId(eventId));
    }


    private Event conventToEntity(EventDTO eventDTO){
        Event event = new Event();
        event.setEventId(eventDTO.getEventId());
        event.setAdminBoardId(eventDTO.getAdminBoardId());
        event.setEventTitle(eventDTO.getEventTitle());
        event.setPoint(eventDTO.getPoint());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());
        event.setLotteryCompleted(eventDTO.isLotteryCompleted());
        event.setEventPoint(eventDTO.isEventPoint());
        event.setPointReward(eventDTO.getPointReward());
        return event;
    }
    private EventDTO convertToDTO(Event event){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventId(event.getEventId());
        eventDTO.setAdminBoardId(event.getAdminBoardId());
        eventDTO.setEventTitle(event.getEventTitle());
        eventDTO.setPoint(event.getPoint());
        eventDTO.setStartDate(event.getStartDate());
        eventDTO.setEndDate(event.getEndDate());
        eventDTO.setLotteryCompleted(event.isLotteryCompleted());
        eventDTO.setEventPoint(event.isEventPoint());
        eventDTO.setPointReward(event.getPointReward());
        return eventDTO;
    }
}
