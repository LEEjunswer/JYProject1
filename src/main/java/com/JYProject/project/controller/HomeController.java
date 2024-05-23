package com.JYProject.project.controller;


import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.service.BoardServiceImpl;
import com.JYProject.project.service.FileServiceImpl;
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemberServiceImpl memberService;
    private final BoardServiceImpl boardService;
    private final FileServiceImpl fileService;
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false); // 세션 존재 확인
        Map<String, Object> params= new HashMap<>();
        params.put("categoryId", 0);
        params.put("offset", 0);
        params.put("pageSize", 5);
        List<BoardDTO> boardWeekBestList = boardService.getWeekBestBoardList(params);
        //   @Comment("카테고리이름 1.자유 2.정보 3.추천 4.후기 나중에 보고 더 추가할 예정")
        List<BoardDTO> boardFreeList  = boardService.boardGetCategoryList(1);
        List<BoardDTO> boardInfoList  = boardService.boardGetCategoryList(2);
        List<BoardDTO> boardRecommendList  = boardService.boardGetCategoryList(3);
        List<BoardDTO> boardReviewList  = boardService.boardGetCategoryList(4);
        List<Long> boardIds = boardWeekBestList.stream()
                .map(BoardDTO::getBoardId)
                .toList();

        List<FileDTO> fileWeekBestList = new ArrayList<>();
        for(Long boardId : boardIds){
            List<FileDTO> files = fileService.getBestFileList(boardId);

            fileWeekBestList.addAll(files);
        }
        model.addAttribute("files", fileWeekBestList);
        model.addAttribute("boards", boardWeekBestList);
        model.addAttribute("freeList" , boardFreeList);
        model.addAttribute("infoList",boardInfoList);
        model.addAttribute("recommendList",boardRecommendList);
        model.addAttribute("reviewList",boardReviewList);
        if (session != null && session.getAttribute(SessionConst.USER_ID) != null) {
            String loginId = (String) session.getAttribute(SessionConst.USER_ID);
            String nickname = (String) session.getAttribute(SessionConst.USER_NAME);

            model.addAttribute("loginId", loginId);
            model.addAttribute("nickname", nickname);
        }
        return "home";
    }
}
