package com.JYProject.project.controller;

import com.JYProject.project.service.FileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileController {

    private final FileServiceImpl service;

    public FileController(FileServiceImpl service) {
        this.service = service;
    }


}
