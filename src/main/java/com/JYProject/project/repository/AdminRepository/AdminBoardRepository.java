package com.JYProject.project.repository.AdminRepository;

import com.JYProject.project.model.AdminBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminBoardRepository {

    Long insertNotice(AdminBoard adminBoard);
    List<AdminBoard> getCategoryList(int category);
    AdminBoard getOneNoticeDetail(Long id);
    List<AdminBoard> getAllNoticeList();
    int boardViewCntIncrease(Long adminBoardId);
}
