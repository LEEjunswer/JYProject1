package com.JYProject.project.interceptor;


import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userId = request.getSession().getAttribute(SessionConst.USER_ID);
        /* 일단 Role같은게 없으니 아이디가 어드민일 경우를 줫다 Grade를 넘겨줄지 고민중이다*/
        if (userId == null || !"admin".equals(userId.toString())) {
            response.sendRedirect("/members/login");
            return false;
        } else {
            return true;
        }


    }
}