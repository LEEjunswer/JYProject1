package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Category;
import com.JYProject.project.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface CategoryRepository {
    public int makeCategory(CategoryDTO categoryDTO);
    public int updateCategory(CategoryDTO categoryDTO);

    public CategoryDTO selectOneCategory(Long id);

    public List<CategoryDTO> getAllCategory();
    //모든 카테고리불러오기
    public List<CategoryDTO> selectAllCategory(Long id);

}
