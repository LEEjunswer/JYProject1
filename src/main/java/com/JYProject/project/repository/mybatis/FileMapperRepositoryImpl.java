package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.File;
import com.JYProject.project.model.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileMapperRepositoryImpl implements FileMapperRepository {
    private  final FileMapperRepository fileMapperRepository;

    @Autowired
    public FileMapperRepositoryImpl(FileMapperRepository fileMapperRepository){
        this.fileMapperRepository = fileMapperRepository;
    }
    @Override
    public int insertFile(File file) {
        return fileMapperRepository.insertFile(file);
    }

    @Override
    public int updateFile(File file) {
        return fileMapperRepository.updateFile(file);
    }

    @Override
    public int deleteFile(Long id) {
        return fileMapperRepository.deleteFile(id);
    }

    //boardId 같을 떄  가져오기
    @Override
    public File getOneFile(Long boardId) {
        return fileMapperRepository.getOneFile(boardId);
    }

    @Override
    public List<File> getBestFileList(Long boardId) {
        return fileMapperRepository.getBestFileList(boardId);
    }
}
