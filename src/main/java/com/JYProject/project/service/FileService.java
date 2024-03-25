package com.JYProject.project.service;

import com.JYProject.project.model.dto.FileDTO;

public interface FileService {

    public  int insertFile(FileDTO fileDTO);
    public int updateFile(FileDTO fileDTO);
    public int deleteFile(Long id);

    public int getOneFile(Long boardId);
}
