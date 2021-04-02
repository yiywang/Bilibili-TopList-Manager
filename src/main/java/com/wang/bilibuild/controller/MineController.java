package com.wang.bilibuild.controller;

import com.wang.bilibuild.mapper.MineMapper;
import com.wang.bilibuild.pojo.Mine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MineController {
    @Autowired
    MineMapper mineMapper;

    //去我们视频库页面
    @RequestMapping("/mine")
    public String toMinePage(Model model){
        Collection<Mine> mines= mineMapper.getAll();
        model.addAttribute("mines",mines);
        return "emps/mine";
    }

    //去修改页面
    @RequestMapping("/updatemine/{bv}")
    public String toEditMine(@PathVariable String bv,Model model){

        Mine mineByBv = mineMapper.getMineByBv(bv);

        model.addAttribute("mine",mineByBv);

        return "emps/update";
    }

    //修改数据
    @PostMapping("/updatemine")
    public String updateMine(Mine mine){
        System.out.println("得到的"+mine);
        Mine mine1 = new Mine(mine.getBv(), mine.getName(), mine.getViews(), mine.getCoins(), mine.getFavor(), mine.getShare(), mine.getLike());
        System.out.println("新的"+mine);
        mineMapper.update(mine1);
        return "redirect:/page";
    }

    //删除数据
    @RequestMapping("/deletemine/{bv}")
    public String deletMine(@PathVariable String bv){
        mineMapper.delete(bv);
        return "redirect:/page";
    }

    //去加新视频页面
    @RequestMapping("/toaddmine")
    public String toAddNew(){

        return "emps/addmine";
    }

    //添加新视频
    @PostMapping("/addmine")
    public String addNew(Mine mine){
        Mine mine1 = new Mine(mine.getBv(), mine.getName(), mine.getViews(), mine.getCoins(), mine.getFavor(), mine.getShare(), mine.getLike());
        mineMapper.save(mine1);
        return "redirect:/page";
    }


    //翻页功能
    @RequestMapping("/page")
    public String page(String indexNo,Model model){

        String spPage=indexNo;

        int pageSize=10;

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
        int count=mineMapper.getCount();
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
        Collection<Mine> mines = mineMapper.pageList(map);
        //最后把信息放入model转发到页面把信息带过去
        model.addAttribute("mines",mines);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);

        //由于设置了一个进度跳，需要一个百分数

        String percent = Integer.toString(pageNo*100/maxPage)+"%";

        model.addAttribute("percent",percent);

        return "emps/mine";
    }





}
