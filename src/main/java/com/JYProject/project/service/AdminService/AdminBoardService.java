package com.JYProject.project.service.AdminService;




import com.JYProject.project.model.dto.AdminBoardDTO;

import java.util.List;

public interface AdminBoardService {
    Long insertNotice(AdminBoardDTO adminBoardDTO);
    List<AdminBoardDTO> getCategoryList(int category);
    AdminBoardDTO getOneNoticeDetail(Long id);
    List<AdminBoardDTO> getAllNoticeList();
    int boardViewCntIncrease(Long id);
}
