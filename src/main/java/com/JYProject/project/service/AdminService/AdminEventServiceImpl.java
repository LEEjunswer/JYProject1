package com.JYProject.project.service.AdminService;

import com.JYProject.project.model.Event;
import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.model.dto.EventDTO;
import com.JYProject.project.repository.AdminRepository.AdminEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


    private Event conventToEntity(EventDTO eventDTO){
        Event event = new Event();
        event.setEventId(eventDTO.getEventId());
        event.setAdminBoardId(eventDTO.getAdminBoardId());
        event.setEventTitle(eventDTO.getEventTitle());
        event.setPoint(eventDTO.getPoint());
        event.setStartDate(eventDTO.getStartDate());
        event.setEndDate(eventDTO.getEndDate());
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
        return eventDTO;
    }
}