package com.JYProject.project.service.CategoryService;

import com.JYProject.project.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    int makeCategory(CategoryDTO categoryDTO);
     int updateCategory(CategoryDTO categoryDTO);
     CategoryDTO selectOneCategory(Long id);
     List<CategoryDTO> getAllCategory();
     List<CategoryDTO> selectAllCategory(Long id);

}
