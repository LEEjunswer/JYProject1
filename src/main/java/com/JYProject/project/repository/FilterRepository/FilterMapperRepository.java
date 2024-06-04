package com.JYProject.project.repository.FilterRepository;

import com.JYProject.project.model.dto.FilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilterMapperRepository {

    public int makeFilter(FilterDTO filterDTO);

    public int deleteFilter(Long id);
    public List<FilterDTO> getAllFilter();

    public FilterDTO getOneFilter(Long id);
}
