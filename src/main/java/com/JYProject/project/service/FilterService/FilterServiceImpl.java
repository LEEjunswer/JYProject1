package com.JYProject.project.service.FilterService;

import com.JYProject.project.model.dto.FilterDTO;
import com.JYProject.project.repository.FilterRepository.FilterMapperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements  FilterService{

    private final FilterMapperRepository filterMybatisRepository;

    public FilterServiceImpl(FilterMapperRepository filterMybatisRepository){
        this.filterMybatisRepository=filterMybatisRepository;
    }

    @Override
    public int makeFilter(FilterDTO filterDTO) {
        return filterMybatisRepository.makeFilter(filterDTO);
    }

    @Override
    public int deleteFilter(Long id) {
        return filterMybatisRepository.deleteFilter(id);
    }

    @Override
    public List<FilterDTO> getAllFilter() {
        return filterMybatisRepository.getAllFilter();
    }

    @Override
    public FilterDTO getOneFilter(Long id) {
        return filterMybatisRepository.getOneFilter(id);
    }
}
