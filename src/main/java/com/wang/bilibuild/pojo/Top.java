package com.wang.bilibuild.pojo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
@NoArgsConstructor
public class Top implements Comparable<Top>{
    private String bv;
    private String name;
    private Integer views;
    private Integer coins;
    private Integer danmuku;
    private Integer favor;
    private Integer share;
    private Integer like;
    private Integer perlike;
    private Integer percoin;
    private Integer peifavor;
    private Date date;
    private String url;


    public Top(String bv, String name, Integer views, Integer coins, Integer danmuku, Integer favor, Integer share, Integer like,String url) {
        this.bv = bv;
        this.name = name;
        this.views = views;
        this.coins = coins;
        this.danmuku = danmuku;
        this.favor = favor;
        this.share = share;
        this.like = like;
        this.peifavor=(favor*1000/views); //为整合浮点数方便，此处定义为千分之多少
        this.percoin=(coins*1000/views);
        this.perlike=(like*1000/views);
        this.date=new Date();
        this.url=url;
    }

    @Override
    public int compareTo(@NotNull Top o) {
        return views.compareTo(o.views);
    }
}
