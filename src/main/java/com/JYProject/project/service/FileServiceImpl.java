package com.JYProject.project.service;

import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.repository.mybatis.FileMybaitsMapperRepository;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService{
    private final FileMybaitsMapperRepository fileMybaitsRepository;

    public FileServiceImpl(FileMybaitsMapperRepository fileMybaitsRepository){
        this.fileMybaitsRepository=fileMybaitsRepository;
    }
    @Override
    public int insertFile(FileDTO fileDTO) {
        return fileMybaitsRepository.insertFile(fileDTO);
    }

    @Override
    public int updateFile(FileDTO fileDTO) {
        return fileMybaitsRepository.updateFile(fileDTO);
    }

    @Override
    public int deleteFile(Long id) {
        return fileMybaitsRepository.deleteFile(id);
    }

    @Override
    public int getOneFile(Long boardId) {
        return fileMybaitsRepository.getOneFile(boardId);
    }
}
