package com.JYProject.project.controller;


import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.service.BoardServiceImpl;
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final MemberServiceImpl memberService;
    private final BoardServiceImpl boardService;
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false); // 세션 존재 확인
        Map<String, Object> params= new HashMap<>();
        params.put("categoryId", 0);
        params.put("offset", 0);
        params.put("pageSize", 5);
        List<BoardDTO> boardWeekBestList = boardService.getWeekBestBoardList(params);
        model.addAttribute("boards", boardWeekBestList);
        if (session != null && session.getAttribute(SessionConst.USER_ID) != null) {
            String loginId = (String) session.getAttribute(SessionConst.USER_ID);
            String nickname = (String) session.getAttribute(SessionConst.USER_NAME);
            model.addAttribute("loginId", loginId);
            model.addAttribute("nickname", nickname);
        }
        return "home";
    }
}
