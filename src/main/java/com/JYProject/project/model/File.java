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
public class File {

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

    @Column
    @Comment("파일등록일자")
    private LocalDateTime regDate;
}
