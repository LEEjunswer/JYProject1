package com.JYProject.project.controller.apiController;


import com.JYProject.project.model.dto.FileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


@RestController
public class FileAPIController{

  //이미지 파일 경로
  @Value("${upload.path}")
  private String uploadPath;

    @PostMapping("/upload-image")
    public Map<String, String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        try {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            file.transferTo(Paths.get(uploadPath, filename));
            String fileUrl = "http://localhost:8082/uploads/" + filename;
            return Collections.singletonMap("fileUrl", fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.singletonMap("error", "Failed to upload file");
        }
    }
}