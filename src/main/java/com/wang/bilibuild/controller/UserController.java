package com.wang.bilibuild.controller;
import com.wang.bilibuild.mapper.UserMapper;
import com.wang.bilibuild.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserMapper userMapper;
    @RequestMapping("/emps")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response){

        String loginUser = (String) request.getSession().getAttribute("loginUser");
        if(userMapper.getStatuesByName(loginUser)==1){

            return "error/statueserror";

        }else {
            Collection<User> users = userMapper.getAll();
            model.addAttribute("emps", users);
            return "emps/list";
        }
    }

    @GetMapping("/emp")
    public String toAddpage(){
        return "emps/add";
    }


    //添加用户
    @PostMapping("/emp")
    public String addEmp(User user){
        List<String> names = userMapper.getNames();

        System.out.println("此时接收到的"+user);
        System.out.println(names.contains(user.getName()));

        if (names.contains(user.getName())){
            userMapper.updateByName(user);

        }else {
            userMapper.save(user);
        }
        return "redirect:/emps";
    }

    //去用户的修改页面
    @GetMapping("/emp/{id}")
    public String toupdatepage(Model model, @PathVariable("id")Integer id){

        User user = userMapper.getUsersById(id);

        model.addAttribute("emp", user);
        return "emps/up";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(User user){

        List<String> names = userMapper.getNames();

        System.out.println("此时接收到的"+user);
        System.out.println(names.contains(user.getName()));

        if (names.contains(user.getName())){
            userMapper.updateByName(user);
        }else {
            userMapper.update(user);
        }
        return "redirect:/emps";
    }

    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id")Integer id){
        userMapper.delete(id);
        return "redirect:/emps";
    }

}

