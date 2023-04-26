package org.ssafy.sample.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.ssafy.sample.member.MemberDto;

@Component
public class SampleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println(request.getRequestURI());
        MemberDto memberDto = (MemberDto) session.getAttribute("MemberDto");
        if (null!=memberDto) {
            return true;
        }
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("login 이 필요한데스웅");
        return false;
    }



}
