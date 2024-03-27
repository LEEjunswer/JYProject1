package com.JYProject.project.controller;


import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private  final MemberServiceImpl memberService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session == null){
        return "home";
        }
        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
        String nickname = (String) session.getAttribute(SessionConst.USER_NAME);
        if (loginId == null) {
            return "home";
        }
        model.addAttribute("loginId", loginId);
        model.addAttribute("nickname",nickname);
        return "home";
    }

}
