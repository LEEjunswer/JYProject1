package com.JYProject.project.service.FileService;

import com.JYProject.project.model.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {

    public  int insertFile(FileDTO fileDTO);
    public int updateFile(FileDTO fileDTO);
    public int deleteFile(Long id);
    Map<String, String> uploadFile(MultipartFile file);
    public FileDTO getOneFile(Long boardId);
    public List<FileDTO> getBestFileList(Long boardId);
}
