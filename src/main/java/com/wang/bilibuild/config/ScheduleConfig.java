package com.wang.bilibuild.config;

import com.wang.bilibuild.mapper.TopMapper;
import com.wang.bilibuild.pojo.Top;
import com.wang.bilibuild.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Collection;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Autowired
    CrawlerService crawlerService;

    @Autowired
    TopMapper topMapper;
    //添加定时任务,爬取数据并存入数据库
    @Scheduled(cron = "0 0 0/1 * * ? ")
    private void configureTasks() {

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

        System.out.println(crawling);
        System.out.println("定时成功的进行了一次爬虫");

    }

}
