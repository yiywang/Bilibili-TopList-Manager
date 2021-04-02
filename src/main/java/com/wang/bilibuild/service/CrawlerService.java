package com.wang.bilibuild.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wang.bilibuild.pojo.Mine;
import com.wang.bilibuild.pojo.Top;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//用于爬取热门榜数据
@Service
public class CrawlerService {



    public Collection<Top> crawling(){

        ArrayList<Top> tops = new ArrayList<>();
        CrawlerService asker =new CrawlerService();
        String url = "https://api.bilibili.com/x/web-interface/popular?ps=20&pn=1";
        String referer = "https://www.bilibili.com/v/popular/all";
        // 获得文本数据
        String content = asker.getContent(url, referer);
        // 根据开发者工具里Preview里的数据结构进行json解析
        Map contentObj = JSON.parseObject(content, Map.class);
        Map data = (Map)contentObj.get("data");
        JSONArray list = (JSONArray) data.get("list");
        for (int i=0;i<list.size();i++){
            Map o = (Map) list.get(i);
            String bv = o.get("bvid").toString();
            String name = o.get("title").toString();
            String burl = o.get("short_link").toString();
            Map stat = (Map) o.get("stat");
            Integer coin = (Integer) stat.get("coin");
            Integer danmaku = (Integer) stat.get("danmaku");
            Integer favorite = (Integer) stat.get("favorite");
            Integer like = (Integer) stat.get("like");
            Integer reply = (Integer) stat.get("reply");
            Integer share = (Integer) stat.get("share");
            Integer view = (Integer) stat.get("view");
            Top top = new Top(bv,name,view,coin,danmaku,favorite,share,like,burl);
            tops.add(top);
        }
        return tops;

    }

    //测试使用
    public Collection<Mine> crawlingMine(){
        System.out.println("执行了爬虫");

        ArrayList<Mine> mines = new ArrayList<>();
        CrawlerService asker2 =new CrawlerService();
        String url = "https://api.bilibili.com/x/web-interface/popular?ps=20&pn=1";
        String referer = "https://www.bilibili.com/v/popular/all";
        // 获得文本数据
        String content = asker2.getContent(url, referer);
        // 根据开发者工具里Preview里的数据结构进行json解析
        Map contentObj = JSON.parseObject(content, Map.class);
        Map data = (Map)contentObj.get("data");
        JSONArray list = (JSONArray) data.get("list");
        for (int i=0;i<list.size();i++){
            Map o = (Map) list.get(i);
            String bv = o.get("bvid").toString();
            String name = o.get("title").toString();
            String burl = o.get("short_link").toString();
            Map stat = (Map) o.get("stat");
            Integer coin = (Integer) stat.get("coin");
            Integer danmaku = (Integer) stat.get("danmaku");
            Integer favorite = (Integer) stat.get("favorite");
            Integer like = (Integer) stat.get("like");
            Integer reply = (Integer) stat.get("reply");
            Integer share = (Integer) stat.get("share");
            Integer view = (Integer) stat.get("view");
            Mine mine = new Mine(bv,name,view,coin,favorite,share,like);
            mines.add(mine);
        }
        return mines;

    }

    //String bv, String name, Integer views, Integer coins, Integer favor, Integer share, Integer like







    public String getContent(String url, String referer) {

        // 先实例化一个 OkHttpClient
        OkHttpClient client = new OkHttpClient();
        // 实例化 Request，定义请求的各种参数；header添加以模拟浏览器访问环境
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .addHeader("Referer", referer)
                //.addHeader("Host","www.bilibili.com")
                .build();

        String result = null;
        // 调用可能失败并抛出异常，需进行捕捉
        try {
            // 构建调用对象
            Call call = client.newCall(request);
            // 请求执行后的结果
            Response rep = call.execute();
            // 获取状态码
            int code = rep.code();
            System.out.println("状态码为"+ code);
            // 获取调用结果，并返回字符串内容的方法（适用请求字符文本文件）
            result = rep.body().string();
        } catch (IOException e) {
            System.out.println("request" + url + " error . ");
            e.printStackTrace();
        }
        client.callTimeoutMillis();
        return result;
    }

}
