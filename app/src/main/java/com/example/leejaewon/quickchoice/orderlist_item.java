package com.example.leejaewon.quickchoice;

/**
 * Created by LeeJaeWon on 2017-05-14.
 */

public class orderlist_item {
    private String no;
    private String start;
    private String desti;
    private String driver;
    private String money;
    private String state;
    private String finalmoney;

    public orderlist_item(String no, String start, String desti, String driver, String money,String state,String finalmoney) {
        this.no=no;
        this.start=start;
        this.desti=desti;
        this.driver=driver;
        this.money=money;
        this.state=state;
        this.finalmoney=finalmoney;
    }

    public String getNo(){
        return no;
    }
    public String getStart(){
        return start;
    }
    public String getDesti(){
        return desti;
    }
    public String getDriver(){
        return driver;
    }
    public String getMoney(){
        return money;
    }
    public String getState(){
        return state;
    }
    public String getFinalmoney(){return finalmoney;}


}
