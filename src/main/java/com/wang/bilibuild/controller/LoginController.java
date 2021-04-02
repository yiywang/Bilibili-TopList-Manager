package com.wang.bilibuild.controller;

import com.wang.bilibuild.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("check") Object check, Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response){

        System.out.println(check.equals("unchecked"));

        if (!StringUtils.isEmpty(username) && userMapper.getUserNames().contains(username) && userMapper.getPwdByName(username).equals(password)){

            if (!check.equals("unchecked")){
                Cookie cookie_username = new Cookie("cookie_username", username);
                cookie_username.setMaxAge(60*60*12);//cookie保持12小时
                cookie_username.setPath(request.getContextPath());
                response.addCookie(cookie_username);
            }
            System.out.println("用户名密码正确");

            session.setAttribute("loginUser",username);

            return "redirect:/home";
        }else {
            //告诉用户你登陆失败了

            System.out.println("用户名密码不正确");
            model.addAttribute("msg","用户名或者密码错误");
            return "index";
        }



    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request,HttpSession session){

        //注销cookie
        Cookie cookie_username = new Cookie("cookie_username", null);
        cookie_username.setMaxAge(0);
        cookie_username.setPath(request.getContextPath());
        response.addCookie(cookie_username);

        //删除session
        session.invalidate();
        return "redirect:/index.html";

    }



}
