package com.JYProject.project.repository.mybatis;


import com.JYProject.project.model.Category;
import com.JYProject.project.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class CategoryMybatisRepository implements CategoryRepository{
    private  final CategoryRepository categoryRepository;

    @Autowired
    public CategoryMybatisRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public int makeCategory(CategoryDTO categoryDTO) {
        return categoryRepository.makeCategory(categoryDTO);
    }

    @Override
    public int updateCategory(CategoryDTO categoryDTO) {
        return categoryRepository.updateCategory(categoryDTO);
    }

    @Override
    public CategoryDTO selectOneCategory(Long id) {
        return categoryRepository.selectOneCategory(id);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public List<CategoryDTO> selectAllCategory(Long id) {
        return categoryRepository.selectAllCategory(id);
    }
}
