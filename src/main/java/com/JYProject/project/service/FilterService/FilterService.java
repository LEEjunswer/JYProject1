package com.JYProject.project.service.FilterService;

import com.JYProject.project.model.dto.FilterDTO;

import java.util.List;

public interface FilterService {

    int makeFilter(String word);

    int deleteFilter(String word);
    List<FilterDTO> getAllFilter();
    List<String> getAllWord();
    FilterDTO getOneFilter(Long id);
}
