package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity{

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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();;

}
