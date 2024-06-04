package com.JYProject.project.repository.FileRepository;

import com.JYProject.project.model.File;
import com.JYProject.project.model.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapperRepository {
    public  int insertFile(File file);
    public int updateFile(File file);
    public int deleteFile(Long id);

    public File getOneFile(Long boardId);
    public List<File> getBestFileList(Long boardId);

}
