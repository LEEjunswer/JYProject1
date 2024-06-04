package com.JYProject.project.service.FileService;

import com.JYProject.project.model.File;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.repository.FileRepository.FileMapperRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileMapperRepository fileRepository;

    //이미지 파일 경로
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public int insertFile(FileDTO fileDTO) {
        return fileRepository.insertFile(convertToEntity(fileDTO));
    }

    @Override
    public int updateFile(FileDTO fileDTO) {

        return fileRepository.updateFile(convertToEntity(fileDTO));
    }

    @Override
    public int deleteFile(Long id) {
        return fileRepository.deleteFile(id);
    }

    @Override
    public Map<String, String> uploadFile(MultipartFile file) {
        System.out.println("file = " + file);
        String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        try {
            java.io.File uploadDir = new java.io.File(uploadPath);
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

    @Override
    public FileDTO getOneFile(Long boardId) {

        return convertToDTO(fileRepository.getOneFile(boardId)) ;
    }

    @Override
    public List<FileDTO> getBestFileList(Long boardId) {
        List<File> fileList = fileRepository.getBestFileList(boardId);
        return fileList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private File convertToEntity(FileDTO fileDTO){
        File file = new File();
        file.setFileId(fileDTO.getFileId());
        file.setFileNames(fileDTO.getFileNames());
        file.setBoardId(fileDTO.getBoardId());
        file.setRegDate(fileDTO.getRegDate());
        return file;
    }
    private FileDTO convertToDTO(File file){
        FileDTO fileDTO = new FileDTO();
            fileDTO.setFileId(file.getFileId());
            fileDTO.setBoardId(file.getBoardId());
            fileDTO.setRegDate(file.getRegDate());
            fileDTO.setFileNames(file.getFileNames());
        return  fileDTO;
    }
}
