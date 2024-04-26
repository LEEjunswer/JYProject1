package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.FilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FilterMapperRepositoryImpl implements FilterMapperRepository {

    private  final FilterMapperRepository filterMapperRepository;

    @Autowired
    public FilterMapperRepositoryImpl(FilterMapperRepository filterMapperRepository){
        this.filterMapperRepository = filterMapperRepository;
    }

    @Override
    public int makeFilter(FilterDTO filterDTO) {
        return filterMapperRepository.makeFilter(filterDTO);
    }

    @Override
    public int deleteFilter(Long id) {
        return filterMapperRepository.deleteFilter(id);
    }

    @Override
    public List<FilterDTO> getAllFilter() {
        return filterMapperRepository.getAllFilter();
    }

    @Override
    public FilterDTO getOneFilter(Long id) {
        return filterMapperRepository.getOneFilter(id);
    }
}
