package com.JYProject.project.service;

import com.JYProject.project.model.File;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.repository.mybatis.FileMapperRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileMapperRepositoryImpl fileMybaitsRepository;

    @Override
    public int insertFile(FileDTO fileDTO) {
        return fileMybaitsRepository.insertFile(convertToEntity(fileDTO));
    }

    @Override
    public int updateFile(FileDTO fileDTO) {

        return fileMybaitsRepository.updateFile(convertToEntity(fileDTO));
    }

    @Override
    public int deleteFile(Long id) {
        return fileMybaitsRepository.deleteFile(id);
    }

    @Override
    public int getOneFile(Long boardId) {
        return fileMybaitsRepository.getOneFile(boardId);
    }

    private File convertToEntity(FileDTO fileDTO){
        File file = new File();
        file.setFileId(fileDTO.getId());
        file.setFileNames(fileDTO.getFileNames());
        file.setBoardId(fileDTO.getBoardId());
        file.setRegDate(fileDTO.getRegDate());
        return file;
    }
    private FileDTO convertToDTO(File file){
        FileDTO fileDTO = new FileDTO();
            fileDTO.setId(file.getFileId());
            fileDTO.setBoardId(file.getBoardId());
            fileDTO.setRegDate(file.getRegDate());
            fileDTO.setFileNames(file.getFileNames());
        return  fileDTO;
    }
}
