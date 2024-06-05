package com.JYProject.project.repository.AdminRepository;

import com.JYProject.project.model.Event;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminEventRepository {
    int insertEvent(Event event);
    Event findAdminBoardId(Long adminBoardId);
}
