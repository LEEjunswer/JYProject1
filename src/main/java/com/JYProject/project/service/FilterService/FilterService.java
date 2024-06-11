package com.JYProject.project.service.FilterService;

import com.JYProject.project.model.dto.FilterDTO;

import java.util.List;

public interface FilterService {

    int makeFilter(FilterDTO filterDTO);

    int deleteFilter(Long id);
    List<FilterDTO> getAllFilter();
    FilterDTO getOneFilter(Long id);
}
