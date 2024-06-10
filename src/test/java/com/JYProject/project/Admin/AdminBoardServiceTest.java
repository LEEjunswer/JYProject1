package com.JYProject.project.Admin;

import com.JYProject.project.model.dto.AdminBoardDTO;
import com.JYProject.project.service.AdminService.AdminBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Component
public class AdminBoardServiceTest {

    @Autowired
    private final AdminBoardService adminBoardService;

    public AdminBoardServiceTest(AdminBoardService adminBoardService) {
        this.adminBoardService = adminBoardService;
    }

    @Test
    public void testInsertNotice() {
        AdminBoardDTO dto = new AdminBoardDTO();
        dto.setMemberId(1L);
        dto.setCategory(0);
        dto.setTitle("Test Title");
        dto.setContent("Test Content");

        Long adminBoardId = adminBoardService.insertNotice(dto);
        System.out.println("Generated AdminBoard ID: " + adminBoardId);

        assertNotNull(adminBoardId);
    }

}
