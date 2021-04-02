package com.wang.bilibuild.controller;


import com.alibaba.fastjson.JSONArray;
import com.wang.bilibuild.mapper.TopMapper;
import com.wang.bilibuild.pojo.Mine;
import com.wang.bilibuild.pojo.Top;
import com.wang.bilibuild.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TopNowController {

    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    TopMapper topMapper;



    @RequestMapping("/home")
    public String crawlerTop(Model model, HttpSession session){

        Collection<Top> crawling = crawlerService.crawling();
        Collection<String> bVs = topMapper.getBVs();
        for (Top top : crawling) {
            if (bVs.contains(top.getBv())){
                topMapper.update(top);
            }else {
                topMapper.save(top);
                System.out.println("添加成功"+top);
            }
        }
        model.addAttribute("tops",crawling);
        return "dashboard";
    }


}
