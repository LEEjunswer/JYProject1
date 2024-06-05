package com.JYProject.project.service.AdminService;

import com.JYProject.project.model.AdminBoard;
import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.repository.AdminRepository.AdminBoardRepository;
import com.JYProject.project.service.FileService.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminBoardServiceImpl implements AdminBoardService {

    private  final AdminBoardRepository adminBoardRepository;
    private final FileService fileService;
    @Override
    public Long insertNotice(AdminBoardDTO adminBoardDTO) {
        String contentChangeImgPath= adminBoardDTO.getContent().replace("../uploads/", "http://localhost:8082/uploads/");
        adminBoardDTO.setContent(contentChangeImgPath);

        return adminBoardRepository.insertNotice(convertToEntity(adminBoardDTO));
    }

    @Override
    public List<AdminBoardDTO> getCategoryList(int category) {
        return adminBoardRepository.getCategoryList(category).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AdminBoardDTO getOneNoticeDetail(Long id) {
        return convertToDTO(adminBoardRepository.getOneNoticeDetail(id));
    }

    @Override
    public List<AdminBoardDTO> getAllNoticeList() {
        return adminBoardRepository.getAllNoticeList().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AdminBoard convertToEntity(AdminBoardDTO adminBoardDTO){
    AdminBoard adminBoard = new AdminBoard();
    adminBoard.setAdminBoardId(adminBoardDTO.getAdminBoardId());
    adminBoard.setContent(adminBoardDTO.getContent());
    adminBoard.setTitle(adminBoardDTO.getTitle());
    adminBoard.setMemberId(adminBoardDTO.getMemberId());
    adminBoard.setCategory(adminBoardDTO.getCategory());
    adminBoard.setDeleteDate(adminBoardDTO.getDeleteDate());
    adminBoard.setRegDate(adminBoardDTO.getRegDate());
    adminBoard.setEndDate(adminBoardDTO.getDeleteDate());
    return  adminBoard;
    }

    public AdminBoardDTO convertToDTO(AdminBoard adminBoard) {
        AdminBoardDTO adminBoardDTO = new AdminBoardDTO();
        adminBoardDTO.setAdminBoardId(adminBoard.getAdminBoardId());
        adminBoardDTO.setContent(adminBoard.getContent());
        adminBoardDTO.setTitle(adminBoard.getTitle());
        adminBoardDTO.setMemberId(adminBoard.getMemberId());
        adminBoardDTO.setCategory(adminBoard.getCategory());
        adminBoardDTO.setDeleteDate(adminBoard.getDeleteDate());
        adminBoardDTO.setRegDate(adminBoard.getRegDate());
        adminBoardDTO.setDeleteDate(adminBoard.getEndDate());
        return adminBoardDTO;
    }
}
