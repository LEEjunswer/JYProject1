package com.JYProject.project.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
    //("파일번호")
    private Long fileId;

    //("글번호")
    private Long boardId;

    //("파일이름")
    private String fileNames;

    //("파일등록일자")
    private LocalDateTime regDate;

    public String getFirstFileName () {
        if (this.fileNames != null && !this.fileNames.isEmpty() && fileNames.contains(",")) {
            fileNames = fileNames.substring(0, fileNames.indexOf(","));
            return fileNames;
        }
        return fileNames;
    }

    public void setFileNameFromList(List<String> fileNameList){
        if(fileNameList != null && !fileNameList.isEmpty()){
            this.fileNames = String.join(",",fileNameList);
        }else {
            this.fileNames = "";
        }
    }
}
