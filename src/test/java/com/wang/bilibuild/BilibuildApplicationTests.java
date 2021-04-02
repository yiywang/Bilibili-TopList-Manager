package com.wang.bilibuild;

import com.jayway.jsonpath.internal.function.numeric.Min;
import com.wang.bilibuild.mapper.MineMapper;
import com.wang.bilibuild.mapper.TopMapper;
import com.wang.bilibuild.mapper.UserMapper;
import com.wang.bilibuild.pojo.Mine;
import com.wang.bilibuild.pojo.Top;
import com.wang.bilibuild.pojo.User;
import com.wang.bilibuild.service.CrawlerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BilibuildApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TopMapper topMapper;

    @Autowired
    MineMapper mineMapper;

    @Autowired
    CrawlerService crawlerService;
    @Test
    void contextLoads() throws Exception {

//        System.out.println(topMapper.update(new Top("test", " String name1", 11111, 111, 111, 111, 11, 1111, "String url")));

//        Collection<Mine> crawling = crawlerService.crawlingMine();
//        Collection<String> bVs = mineMapper.getBVs();
//        for (Mine top : crawling) {
//            if (bVs.contains(top.getBv())){
//                mineMapper.update(top);
//            }else {
//                mineMapper.save(top);
//            }
//
//        }

//
//
//        Collection<Top> tops = topMapper.pageList(map);
//
//        for (Top top : tops) {
//            System.out.println(top);
//
//        }

//        List<String> names = userMapper.getNames();
//
//        System.out.println(names);

//        Integer admin = userMapper.getStatuesByName("admin");
//        System.out.println(admin);


        Collection<Top> crawling = crawlerService.crawling();
        Collection<String> bVs = topMapper.getBVs();
        for (Top top : crawling) {
            if (bVs.contains(top.getBv())){
                topMapper.update(top);
            }else {
                topMapper.save(top);
            }

        }

        System.out.println(crawling);

        System.out.println("定时成功的进行了一次爬虫");


    }

}
