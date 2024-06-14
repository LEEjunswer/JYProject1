package com.JYProject.project.repository.FilterRepository;

import com.JYProject.project.model.Filter;
import com.JYProject.project.model.dto.FilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilterMapperRepository {

     int makeFilter(Filter Filter);

     int deleteFilter(Long id);
     List<Filter> getAllFilter();

     Filter getOneFilter(Long id);
     Filter findByWord(String word);
}
