package com.JYProject.project.filter;

import com.JYProject.project.session.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.util.UUID;

@Slf4j

public class LoginCheckFilter implements Filter {
    private static final String[] acceptHtml = {"/", "members/join","members/loginForm","boards/content","/css"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        try {
            log.info("인증 체크 시작", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로그 실행",requestURI);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.USER_ID )== null) {
                    log.info("미인증 사용자 요청{}" ,requestURI);
                    //로그인화면으로 redirect 이동;
                    //나중에 다 수정 할 예정
                    httpServletResponse.sendRedirect("members/loginForm?redirectURL=" + requestURI);
                    return;
                    }
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }catch(Exception e) {
            throw  e;
            }finally {
            log.info("인증체크 필터 종료{}",requestURI  );
        }
    }
        /**
         * 로그인 필요하지 않을경우만 진입
         */
        private boolean isLoginCheckPath(String requestURI){
            return !PatternMatchUtils.simpleMatch(acceptHtml,requestURI);

        }
    }



