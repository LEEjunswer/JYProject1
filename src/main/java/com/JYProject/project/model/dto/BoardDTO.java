package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("게시판번호")
    private Long id;

    @Comment("게시판의 이름")
    private String name;

    @JoinColumn(name="post_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();
}
