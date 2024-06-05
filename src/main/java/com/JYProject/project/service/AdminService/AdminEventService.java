package com.JYProject.project.service.AdminService;


import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.model.dto.EventDTO;

public interface AdminEventService {
    int insertEvent(EventDTO eventDTO, AdminBoardDTO adminBoardDTO);
    EventDTO findAdminBoardId(Long adminBoardId);
}
