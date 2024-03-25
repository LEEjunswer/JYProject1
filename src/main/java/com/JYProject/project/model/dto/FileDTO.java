package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    @Comment("파일번호")
    private Long id;

    @Comment("글번호")
    private Long boardNo;

    @Comment("파일이름")
    private String fileName;

    @Comment("파일등록일자")
    private LocalDateTime regDate;
}
