package com.JYProject.project.model.dto;

import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Data
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