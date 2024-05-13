package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Comment("파일번호")
    private Long fileId;

    @Comment("글번호")
    private Long boardId;

    @Comment("파일이름")
    private List<String> fileNames;

    @Comment("파일등록일자")
    private LocalDateTime regDate;
}
