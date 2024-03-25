package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.FilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilterMybatisRepository implements FilterRepository{

    private  final FilterRepository filterRepository;

    @Autowired
    public  FilterMybatisRepository(FilterRepository filterRepository){
        this.filterRepository=filterRepository;
    }

    @Override
    public int makeFilter(FilterDTO filterDTO) {
        return filterRepository.makeFilter(filterDTO);
    }

    @Override
    public int deleteFilter(Long id) {
        return filterRepository.deleteFilter(id);
    }

    @Override
    public List<FilterDTO> getAllFilter() {
        return filterRepository.getAllFilter();
    }

    @Override
    public FilterDTO getOneFilter(Long id) {
        return filterRepository.getOneFilter(id);
    }
}
