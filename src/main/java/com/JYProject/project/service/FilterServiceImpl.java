package com.JYProject.project.service;

import com.JYProject.project.model.dto.FilterDTO;
import com.JYProject.project.repository.mybatis.FilterMybatisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements  FilterService{

    private final FilterMybatisRepository filterMybatisRepository;

    public FilterServiceImpl(FilterMybatisRepository filterMybatisRepository){
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
