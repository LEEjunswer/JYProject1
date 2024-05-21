package com.JYProject.project.model.dto;

import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    @Comment("파일번호")
    private Long id;

    @Comment("글번호")
    private Long boardId;

    @Comment("파일이름")
    private String fileNames;

    @Comment("파일등록일자")
    private LocalDateTime regDate;

    public List<String> getFileNamesList() {
        if (this.fileNames != null && !this.fileNames.isEmpty()) {
            return Arrays.asList(this.fileNames.split(","));
        }
        return new ArrayList<>();
    }

    public void setFileNameFromList(List<String> fileNameList){
        if(fileNameList != null && !fileNameList.isEmpty()){
            this.fileNames = String.join(",",fileNameList);
        }else {
            this.fileNames = "";
        }
    }
}
