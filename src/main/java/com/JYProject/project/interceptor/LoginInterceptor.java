package com.JYProject.project.interceptor;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object Handler)throws  Exception{
        if(request.getSession().getAttribute("id") == null){
            response.sendRedirect(request.getContextPath() + "/login");
            String uuid = UUID.randomUUID().toString();

            return false;
        }
        return true;
    }
}
