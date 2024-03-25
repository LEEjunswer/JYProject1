package com.JYProject.project.service;

import com.JYProject.project.model.dto.FilterDTO;

import java.util.List;

public interface FilterService {

    public int makeFilter(FilterDTO filterDTO);

    public int deleteFilter(Long id);
    public List<FilterDTO> getAllFilter();

    public FilterDTO getOneFilter(Long id);
}
