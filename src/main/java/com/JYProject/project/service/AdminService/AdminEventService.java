package com.JYProject.project.service.AdminService;


import com.JYProject.project.model.Event;
import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.model.dto.EventDTO;

import java.util.List;

public interface AdminEventService {
    int insertEvent(EventDTO eventDTO, AdminBoardDTO adminBoardDTO);
    EventDTO findAdminBoardId(Long adminBoardId);
    List<EventDTO> findDuringEvent();
    int updateEventEnd(Long eventId);
}
