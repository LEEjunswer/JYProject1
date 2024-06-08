package com.JYProject.project.repository.AdminRepository;

import com.JYProject.project.model.Event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminEventRepository {
    int insertEvent(Event event);
    Event findAdminBoardId(Long adminBoardId);
    List<Event> findDuringEvent();
    int updateEventEnd(Long eventId);
}
