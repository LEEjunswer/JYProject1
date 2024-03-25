package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRepository {
    public  int insertFile(FileDTO fileDTO);
    public int updateFile(FileDTO fileDTO);
    public int deleteFile(Long id);

    public int getOneFile(Long boardId);

}
