package com.JYProject.project.controller.apiController;

import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.AdminService.AdminEventService;
import com.JYProject.project.service.EventApplicantService.EventApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeAPIController {
    private final EventApplicantService eventApplicantService;
    private final AdminBoardService adminBoardService;
    private final AdminEventService adminEventService;

}
