package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.BoardResponseDTO;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.service.BoardService.BoardServiceImpl;
import com.JYProject.project.service.FileService.FileServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeApIController {
    private final BoardServiceImpl boardService;
    private final FileServiceImpl fileService;

    @RequestMapping(value = "/home/bestWeekBoard", method = RequestMethod.GET)
    public ResponseEntity<BoardResponseDTO> getBoardWeekBest(@RequestParam(defaultValue =  "0") int categoryId, @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "5")int pageSize) {
        //MyBatis Page 계산방법
        int offset = (page - 1 );
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId" , categoryId);
        params.put("offset",offset);
        params.put("pageSize",pageSize);
        System.out.println("weekParams = " + params);
        List<BoardDTO> boardList = boardService.getWeekBestBoardList(params);
        List<Long> boardIds = boardList.stream()
                .map(BoardDTO::getBoardId)
                .toList();

        List<FileDTO> fileWeekBestList = new ArrayList<>();
        for(Long boardId : boardIds){
            List<FileDTO> files = fileService.getBestFileList(boardId);

            fileWeekBestList.addAll(files);
        }

        BoardResponseDTO  responseDTO= new BoardResponseDTO();
        responseDTO.setBoardList(boardList);
        responseDTO.setFileWeekBestList(fileWeekBestList);
        return ResponseEntity.ok().body(responseDTO);
    }
    @RequestMapping(value="/home/bestDailyBoard",method = RequestMethod.GET)
    public ResponseEntity<BoardResponseDTO> getBoardDaily(@RequestParam(defaultValue = "0")int categoryId,@RequestParam(defaultValue = "1")int page ,@RequestParam(defaultValue = "5")int pageSize){
        int offset = (page -1);
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId",categoryId);
        params.put("offset",offset);
        params.put("pageSize",pageSize);
        System.out.println("dailyParams = " + params);
        List<BoardDTO> boardList = boardService.getDailyBestBoardList(params);
        List<Long> boardIds = boardList.stream()
                .map(BoardDTO::getBoardId)
                .toList();

        List<FileDTO> fileWeekBestList = new ArrayList<>();
        for(Long boardId : boardIds){
            List<FileDTO> files = fileService.getBestFileList(boardId);

            fileWeekBestList.addAll(files);
        }
        System.out.println("fileWeekBestList = " + fileWeekBestList);
        System.out.println("boardList = " + boardList);
        BoardResponseDTO  responseDTO= new BoardResponseDTO();
        responseDTO.setBoardList(boardList);
        responseDTO.setFileWeekBestList(fileWeekBestList);
        return ResponseEntity.ok().body(responseDTO);
    }

}
