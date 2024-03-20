package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("게시판번호")
    @Column(name="board_id")
    private Long id;

    @Comment("게시판의 이름")
    private String name;

    @Column
    @Comment("작성자")
    private String  writer;

    @Column
    @Comment("글제목")
    private String title;

    @Column
    @Comment("글내용")
    private String content;

    @Column
    @Comment("삭제날짜")
    private LocalDateTime  deleteDate;

    @Comment("조회수")
    private int viewCnt;

    @Column
    @Comment("좋아요")
    private Long likes;

    @Column
    @Comment("싫어요")
    private Long dislikes;

    @JoinColumn(name="member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name="category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY)
    private List<File> FileList = new ArrayList<>();;
}
