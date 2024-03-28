package com.JYProject.project.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
=======
>>>>>>> main
public class CategoryDTO {

    private Long id;
    private String branch;
    private String code;
    private String name;
    private Integer level;
    private CategoryDTO parentCategory;
    private List<CategoryDTO> subCategory = new ArrayList<>();
    private BoardDTO boardDTO;
}
