package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.service.BoardServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeApIController {
    private final BoardServiceImpl boardService;

    @RequestMapping(value = "/home/bestWeekBoard", method = RequestMethod.GET)
    public ResponseEntity<List<BoardDTO>> getBoardWeekBest(@RequestParam(defaultValue =  "0") int categoryId,@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "5")int pageSize) {
        //MyBatis Page 계산방법

        int offset = (page - 1 );
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId" , categoryId);
        params.put("offset",offset);
        params.put("pageSize",pageSize);
        List<BoardDTO> boardList = boardService.getWeekBestBoardList(params);
        return ResponseEntity.ok().body(boardList);
    }
    @RequestMapping(value="/home/bestDailyBoard",method = RequestMethod.GET)
    public ResponseEntity<List<BoardDTO>> getBoardDaily(@RequestParam(defaultValue = "0")int categoryId,@RequestParam(defaultValue = "1")int page ,@RequestParam(defaultValue = "5")int pageSize){
        int offset = (page -1);
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId",categoryId);
        params.put("offset",offset);
        params.put("pageSize",pageSize);
        List<BoardDTO> boardList = boardService.getDailyBestBoardList(params);
        return ResponseEntity.ok().body(boardList);
    }
}
