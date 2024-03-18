package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("필터번호")
    private Long id;
    @Column
    @Comment("필터할단어")
    private String word;
    @Column
    @Comment("등록일자")
    private LocalDateTime createAt;
}
