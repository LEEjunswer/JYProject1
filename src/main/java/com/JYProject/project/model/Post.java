package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("게시글번호")
    @Column(name="post_id")
    private Long id;

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

    @Column
    @Comment("좋아요")
    private Long likes;

    @Column
    @Comment("싫어요")
    private Long dislikes;

    @JoinColumn(name="member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name="board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @JoinColumn(name="category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Reply> replyList = new ArrayList<>();

}
