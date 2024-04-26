package com.JYProject.project.service;

import com.JYProject.project.model.Category;
import com.JYProject.project.model.dto.CategoryDTO;
import com.JYProject.project.repository.mybatis.CategoryMapperRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements  CategoryService{

    private final CategoryMapperRepositoryImpl categoryMybatisRepository;

    public CategoryServiceImpl(CategoryMapperRepositoryImpl categoryMybatisRepository){
        this.categoryMybatisRepository = categoryMybatisRepository;
    }

    @Override
    public int makeCategory(CategoryDTO categoryDTO) {

        return categoryMybatisRepository.makeCategory(convertToEntity(categoryDTO));
    }

    @Override
    public int updateCategory(CategoryDTO categoryDTO) {

        return categoryMybatisRepository.updateCategory(convertToEntity(categoryDTO));
    }

    @Override
    public CategoryDTO selectOneCategory(Long id) {

        return convertToDTO(categoryMybatisRepository.selectOneCategory(id));
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
    List<Category> category =categoryMybatisRepository.getAllCategory();
        return category.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> selectAllCategory(Long id) {
    List<Category>  category=categoryMybatisRepository.selectAllCategory(id);
        return category.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Category convertToEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryId(categoryDTO.getId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setRegDate(categoryDTO.getRegDate());
        return  category;
    }
    private CategoryDTO convertToDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setRegDate(category.getRegDate());
        return  categoryDTO;
    }
}
