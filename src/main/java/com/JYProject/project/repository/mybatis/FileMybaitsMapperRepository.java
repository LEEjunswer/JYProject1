package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileMybaitsMapperRepository implements FileMapperRepository {
    private  final FileMapperRepository fileMapperRepository;

    @Autowired
    public FileMybaitsMapperRepository(FileMapperRepository fileMapperRepository){
        this.fileMapperRepository = fileMapperRepository;
    }
    @Override
    public int insertFile(FileDTO fileDTO) {
        return fileMapperRepository.insertFile(fileDTO);
    }

    @Override
    public int updateFile(FileDTO fileDTO) {
        return fileMapperRepository.updateFile(fileDTO);
    }

    @Override
    public int deleteFile(Long id) {
        return fileMapperRepository.deleteFile(id);
    }

    //boardId 같을 떄  가져오기
    @Override
    public int getOneFile(Long boardId) {
        return fileMapperRepository.getOneFile(boardId);
    }
}
