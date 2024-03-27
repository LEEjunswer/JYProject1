package com.JYProject.project.repository.mybatis;


import com.JYProject.project.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class CategoryMybatisMapperRepository implements CategoryMapperRepository {
    private  final CategoryMapperRepository categoryMapperRepository;

    @Autowired
    public CategoryMybatisMapperRepository(CategoryMapperRepository categoryMapperRepository){
        this.categoryMapperRepository = categoryMapperRepository;
    }
    @Override
    public int makeCategory(CategoryDTO categoryDTO) {
        return categoryMapperRepository.makeCategory(categoryDTO);
    }

    @Override
    public int updateCategory(CategoryDTO categoryDTO) {
        return categoryMapperRepository.updateCategory(categoryDTO);
    }

    @Override
    public CategoryDTO selectOneCategory(Long id) {
        return categoryMapperRepository.selectOneCategory(id);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return categoryMapperRepository.getAllCategory();
    }

    @Override
    public List<CategoryDTO> selectAllCategory(Long id) {
        return categoryMapperRepository.selectAllCategory(id);
    }
}
