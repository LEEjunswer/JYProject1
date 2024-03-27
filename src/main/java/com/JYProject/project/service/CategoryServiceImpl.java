package com.JYProject.project.service;

import com.JYProject.project.model.dto.CategoryDTO;
import com.JYProject.project.repository.mybatis.CategoryMybatisMapperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements  CategoryService{

    private final CategoryMybatisMapperRepository categoryMybatisRepository;

    public CategoryServiceImpl(CategoryMybatisMapperRepository categoryMybatisRepository){
        this.categoryMybatisRepository = categoryMybatisRepository;
    }

    @Override
    public int makeCategory(CategoryDTO categoryDTO) {
        return categoryMybatisRepository.makeCategory(categoryDTO);
    }

    @Override
    public int updateCategory(CategoryDTO categoryDTO) {
        return categoryMybatisRepository.updateCategory(categoryDTO);
    }

    @Override
    public CategoryDTO selectOneCategory(Long id) {
        return categoryMybatisRepository.selectOneCategory(id);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return categoryMybatisRepository.getAllCategory();
    }

    @Override
    public List<CategoryDTO> selectAllCategory(Long id) {
        return categoryMybatisRepository.selectAllCategory(id);
    }
}
