package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String branch;

    private String code;

    private String name;

    private Integer level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="parent_cagegory_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategory = new ArrayList<>();


    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BoardDTO boardDTO;

}
