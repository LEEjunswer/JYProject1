package com.JYProject.project.controller;

import com.JYProject.project.service.FileService.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileServiceImpl fileService;



}
