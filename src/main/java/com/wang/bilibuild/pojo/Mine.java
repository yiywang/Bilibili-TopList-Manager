package com.wang.bilibuild.pojo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mine {
    private String bv;
    private String name;
    private Integer views;
    private Integer coins;
    private Integer favor;
    private Integer share;
    private Integer like;
    private Integer perlike;
    private Integer percoin;
    private Integer peifavor;
    private String url;

    public Mine(String bv, String name, Integer views, Integer coins, Integer favor, Integer share, Integer like) {
        this.bv = bv;
        this.name = name;
        this.views = views;
        this.coins = coins;
        this.favor = favor;
        this.share = share;
        this.like = like;
        this.peifavor=(favor*1000/views);//为整合浮点数方便，此处定义为千分之多少
        this.percoin=(coins*1000/views);
        this.perlike=(like*1000/views);
        this.url="https://b23.tv/"+bv;
    }
}
