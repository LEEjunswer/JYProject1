package com.JYProject.project.interceptor;

import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();
        String loginPath = request.getContextPath() + "/members/login";
        String[] protectedPaths = { "/boards/join", "/boards/delete", "/boards/update", "/members/profile","/members/delete","/members/updatex" };
        boolean isProtectedPath = false;
        for (String path : protectedPaths) {
            if (uri.startsWith(request.getContextPath() + path)) {
                isProtectedPath = true;
                break;
            }
        }
        if (isProtectedPath) {
            if (session == null || session.getAttribute(SessionConst.USER_ID) == null) {
                if (!uri.equals(loginPath)) {
                    response.sendRedirect(loginPath);
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && request.getSession() != null) {
            HttpSession session = request.getSession();
            String loginId = (String) session.getAttribute(SessionConst.USER_ID);
            String nickname = (String) session.getAttribute(SessionConst.USER_NAME);
            modelAndView.addObject("loginId", loginId);
            modelAndView.addObject("nickname", nickname);
        }
    }

}
