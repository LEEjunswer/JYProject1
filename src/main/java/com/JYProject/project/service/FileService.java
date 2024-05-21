package com.JYProject.project.service;

import com.JYProject.project.model.dto.FileDTO;

import java.util.List;

public interface FileService {

    public  int insertFile(FileDTO fileDTO);
    public int updateFile(FileDTO fileDTO);
    public int deleteFile(Long id);

    public FileDTO getOneFile(Long boardId);
    public List<FileDTO> getBestFileList(Long boardId);
}
