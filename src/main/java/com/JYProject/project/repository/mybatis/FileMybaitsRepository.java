package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileMybaitsRepository implements FileRepository {
    private  final FileRepository fileRepository;

    @Autowired
    public FileMybaitsRepository(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }
    @Override
    public int insertFile(FileDTO fileDTO) {
        return fileRepository.insertFile(fileDTO);
    }

    @Override
    public int updateFile(FileDTO fileDTO) {
        return fileRepository.updateFile(fileDTO);
    }

    @Override
    public int deleteFile(Long id) {
        return fileRepository.deleteFile(id);
    }

    //boardId 같을 떄  가져오기
    @Override
    public int getOneFile(Long boardId) {
        return fileRepository.getOneFile(boardId);
    }
}
