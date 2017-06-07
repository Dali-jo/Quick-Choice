package com.example.leejaewon.quickchoice;

/**
 * Created by LeeJaeWon on 2017-05-26.
 */

public class riderlist_item {
    private String name;
    private String money;
    private String point;
    private String riderid;
    private String no;


    public riderlist_item(String riderid,String money,String no,String name) {
        this.riderid=riderid;
        this.money=money;
        this.no=no;
        this.name=name;
    }

    public String getriderid(){return riderid;}
    public String getMoney(){return money;}
    public String getNo(){return no;}
    public String getName(){return name;}


}
