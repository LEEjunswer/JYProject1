package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.File;
import com.JYProject.project.model.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapperRepository {
    public  int insertFile(File file);
    public int updateFile(File file);
    public int deleteFile(Long id);

    public int getOneFile(Long boardId);

}
