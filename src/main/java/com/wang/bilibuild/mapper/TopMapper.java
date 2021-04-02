package com.wang.bilibuild.mapper;

import com.wang.bilibuild.pojo.Mine;
import com.wang.bilibuild.pojo.Top;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Mapper
@Repository
public interface TopMapper {


    //得到所有的视频
    Collection<Top> getAll();

    //得到本月的视频
    Collection<Top> getThisMonth(Map map);

    //得到本月的视频总数
    int getThisMonthCount();

    //得到总数
    int getCount();


    //分页查询
    Collection<Top> pageList(Map map);

    //得到所有BV号
    Collection<String> getBVs();


    //存入新的视频
    int save(Top top);



    //更新视频信息
    int update(Top top);





}
