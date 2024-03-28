package com.JYProject.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("파일번호")
    @Column(name="file_id")
    private Long id;

    @Column
    @Comment("글번호")
    private Long boardNo;

    @Column
    @Comment("파일이름")
    private String fileName;

    @JoinColumn(name="board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
