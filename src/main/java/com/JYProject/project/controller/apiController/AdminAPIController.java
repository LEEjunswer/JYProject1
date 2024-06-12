package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.AdminService.AdminEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminAPIController {
    private final AdminBoardService adminBoardService;
    private final AdminEventService adminEventService;
    private final FileAPIController fileAPIController;


}
