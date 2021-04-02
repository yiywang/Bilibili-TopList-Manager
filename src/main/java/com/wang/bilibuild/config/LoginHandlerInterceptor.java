package com.wang.bilibuild.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //先拿一下用户的cookie
        Cookie[] cookies = request.getCookies();
        String cookie_username = null;
        for (Cookie item : cookies){
            if (item.getName().equals("cookie_username")){
                cookie_username=item.getValue();
                break;
            }
        }


        if (cookie_username!=null){
            request.getSession().setAttribute("loginUser",cookie_username);
            return true;
        }

        Object loginUser = request.getSession().getAttribute("loginUser");

        if (loginUser==null){
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("index.html").forward(request,response);
            return false;
        }else{
            return true;
        }
    }
}
