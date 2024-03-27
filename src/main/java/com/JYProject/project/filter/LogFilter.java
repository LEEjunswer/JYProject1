package com.JYProject.project.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();
        try{
        log.info("REQUEST [{}][{}]",uuid,requestURI);
        chain.doFilter(request, response);  //이게 없으면 가지고 올 수 없다
        }catch(Exception e){
            throw e;
        }finally {
            log.info("REQUEST [{}][{}]",uuid,requestURI);
        }
    }


}
