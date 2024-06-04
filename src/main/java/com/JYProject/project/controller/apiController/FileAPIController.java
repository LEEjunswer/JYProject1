package com.JYProject.project.controller.apiController;


import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.service.FileService.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


@RestController
@RequiredArgsConstructor
public class FileAPIController{

    FileService fileService;

    @PostMapping("/upload-image")
    public Map<String, String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        return  fileService.uploadFile(file);
    }
}