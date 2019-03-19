package com.ydy.recyclerview.bean;

/**
 * **************************************************
 * 文件名称: News
 * 创建作者: Created by ydy
 * 创建时间: 2019/3/18 16:37
 * 文件描述:
 * 注意事项:
 * 修改历史: 2019/3/18 初始版本
 * **************************************************
 */
public class News {


    /**
     * big_news_flag : 0
     * content : 近日主要厂家停报，市场购销好转，3月6日北沙制药提高VE产品报价至48元/公斤，欧洲市场报价4.3欧元/公斤左右。（饲料巴巴）
     * title : 北沙制药提高VE报价
     * rn : 1
     * plateids : 17906113,维生素
     * showtime : 2019-03-07 10:13:23.0
     * stocks : 新 和 成,002001.SZ
     * impact : 利好
     */

    private String big_news_flag;
    private String content;
    private String title;
    private String rn;
    private String plateids;
    private String showtime;
    private String stocks;
    private String impact;

    public String getBig_news_flag() {
        return big_news_flag;
    }

    public void setBig_news_flag(String big_news_flag) {
        this.big_news_flag = big_news_flag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getPlateids() {
        return plateids;
    }

    public void setPlateids(String plateids) {
        this.plateids = plateids;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }
}
