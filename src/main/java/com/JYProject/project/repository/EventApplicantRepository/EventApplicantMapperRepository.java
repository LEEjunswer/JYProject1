package com.JYProject.project.repository.EventApplicantRepository;

import com.JYProject.project.model.EventApplicant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventApplicantMapperRepository {
    int insertApplicant(EventApplicant eventApplicant);
    EventApplicant findEventIdAndMemberId(EventApplicant eventApplicant);
}
