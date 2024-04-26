package com.JYProject.project.repository.mybatis;


import com.JYProject.project.model.Category;
import com.JYProject.project.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class CategoryMapperRepositoryImpl implements CategoryMapperRepository {
    private  final CategoryMapperRepository categoryMapperRepository;

    @Autowired
    public CategoryMapperRepositoryImpl(CategoryMapperRepository categoryMapperRepository){
        this.categoryMapperRepository = categoryMapperRepository;
    }
    @Override
    public int makeCategory(Category category) {
        return categoryMapperRepository.makeCategory(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapperRepository.updateCategory(category);
    }

    @Override
    public Category selectOneCategory(Long id) {
        return categoryMapperRepository.selectOneCategory(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapperRepository.getAllCategory();
    }

    @Override
    public List<Category> selectAllCategory(Long id) {
        return categoryMapperRepository.selectAllCategory(id);
    }
}
