package com.JYProject.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

    //("파일번호")
    private Long fileId;

    //("글번호")
    private Long boardId;

    //("파일이름")
    private String fileNames;

    //("파일등록일자")
    private LocalDateTime regDate;

    private List<String> getFileNamesList(String fileNames) {
        if (this.fileNames != null && !this.fileNames.isEmpty()) {
            return Arrays.asList(this.fileNames.split(","));
        }
        return new ArrayList<>();
    }

    private void setFileNameFromList(List<String> fileNameList){
        if(fileNameList != null && !fileNameList.isEmpty()){
            this.fileNames = String.join(",",fileNameList);
        }else {
            this.fileNames = "";
        }
    }
}