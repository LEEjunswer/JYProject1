package com.JYProject.project.service;

import com.JYProject.project.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    public int makeCategory(CategoryDTO categoryDTO);
    public int updateCategory(CategoryDTO categoryDTO);

    public CategoryDTO selectOneCategory(Long id);

    public List<CategoryDTO> getAllCategory();
    public List<CategoryDTO> selectAllCategory(Long id);

}
