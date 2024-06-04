package com.JYProject.project.repository.CategoryRepository;

import com.JYProject.project.model.Category;
import com.JYProject.project.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CategoryMapperRepository {
    public int makeCategory(Category category);
    public int updateCategory(Category category);

    public Category selectOneCategory(Long id);

    public List<Category> getAllCategory();
    //모든 카테고리불러오기
    public List<Category> selectAllCategory(Long id);

}
