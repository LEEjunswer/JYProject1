package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
