package com.wang.bilibuild.controller;

import com.wang.bilibuild.mapper.TopMapper;
import com.wang.bilibuild.pojo.Mine;
import com.wang.bilibuild.pojo.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HisTopController {
    @Autowired
    TopMapper topMapper;



    //翻页功能
    @RequestMapping("/his")
    public String page(String indexNo,Model model){

        String spPage=indexNo;

        int pageSize=15;

        int pageNo=0;

        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=topMapper.getCount();
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;

        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("indexNo",tempPageNo);
        map.put("pageSize",pageSize);
        Collection<Top> tops = topMapper.pageList(map);

        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("tops",tops);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);

        //由于设置了一个进度跳，需要一个百分数

        String percent = Integer.toString(pageNo*100/maxPage)+"%";

        model.addAttribute("percent",percent);

        return "emps/histop";
    }





    @RequestMapping("/thismonth")
    public String MonthPage(String indexNo,Model model){

        String spPage=indexNo;

        int pageSize=15;

        int pageNo=0;

        if(spPage==null){
            pageNo=1;
        }else {
            pageNo = Integer.valueOf(spPage);
            if (pageNo < 1) {
                pageNo = 1;
            }
        }
        //设置最大页数
        int totalCount=0;
        int count=topMapper.getThisMonthCount();
        if(count>0){
            totalCount=count;
        }
        int maxPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;

        if(pageNo>maxPage){
            pageNo=maxPage;
        }
        int tempPageNo=(pageNo-1)*pageSize;
        //计算总数量
        //分页查询
        Map map=new HashMap();
        map.put("indexNo",tempPageNo);
        map.put("pageSize",pageSize);
        Collection<Top> tops = topMapper.getThisMonth(map);



        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("tops",tops);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);

        //由于设置了一个进度跳，需要一个百分数

        String percent = Integer.toString(pageNo*100/maxPage)+"%";

        model.addAttribute("percent",percent);

        return "emps/thismonth";
    }

}
