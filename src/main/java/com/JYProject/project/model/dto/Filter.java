package com.JYProject.project.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("필터번호")
    @Column(name="filter_id")
    private Long id;

    @Column
    @Comment("필터할단어")
    private String word;

    @Column
    @Comment("등록일자")
    private LocalDateTime createAt;
}
