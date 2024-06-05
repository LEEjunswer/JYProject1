package com.JYProject.project.service.EventApplicantService;

import com.JYProject.project.model.dto.EventApplicantDTO;

public interface EventApplicantService {
    int insertApplicant(EventApplicantDTO eventApplicantDTO);
    EventApplicantDTO findEventIdAndMemberId(EventApplicantDTO eventApplicantDTO);
}
