package com.wang.bilibuild.mapper;

import com.wang.bilibuild.pojo.Mine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Mapper
@Repository
public interface MineMapper {

    //得到所有的bv号
    Collection<String> getBVs();


    //得到所有的视频
    Collection<Mine> getAll();


    //由BV号得到视频
    Mine getMineByBv(@Param("bv") String bv);


    //存入新的视频
     int save(Mine mine);



     //更新视频信息
     int update(Mine mine);



     //由BV号删除视频
     int delete(@Param("bv") String bv);


     //得到总数
    int getCount();


    //分页查询
    Collection<Mine> pageList(Map map);
}
