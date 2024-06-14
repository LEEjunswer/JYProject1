package com.JYProject.project.service.FilterService;

import com.JYProject.project.model.Filter;
import com.JYProject.project.model.dto.FilterDTO;
import com.JYProject.project.repository.FilterRepository.FilterMapperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements  FilterService{

    private final FilterMapperRepository filterMybatisRepository;


    @Override
    public int makeFilter(String word) {
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setWord(word);
        return filterMybatisRepository.makeFilter(convertToEntity(filterDTO));
    }

    @Override
    public int deleteFilter(String word) {
        Filter filter = filterMybatisRepository.findByWord(word);
        if(filter == null){
            return 0;
        }
        System.out.println("filter = " + filter);
        return filterMybatisRepository.deleteFilter(filter.getFilterId());
    }

    @Override
    public List<FilterDTO> getAllFilter() {

        return filterMybatisRepository.getAllFilter().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public FilterDTO getOneFilter(Long id) {

        return  convertToDTO(filterMybatisRepository.getOneFilter(id));
    }

    public Filter convertToEntity(FilterDTO filterDTO){
        Filter  filter = new Filter();
        filter.setFilterId(filterDTO.getFilterId());
        filter.setWord(filterDTO.getWord());
        filter.setCreateAt(filterDTO.getCreateAt());
        return filter;
    }
    public FilterDTO convertToDTO(Filter filter){
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setFilterId(filter.getFilterId());
        filterDTO.setWord(filter.getWord());
        filterDTO.setCreateAt(filterDTO.getCreateAt());
        return filterDTO;
    }

}
