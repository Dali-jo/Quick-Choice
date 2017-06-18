package com.example.leejaewon.quickchoice;

import android.widget.ImageView;

/**
 * Created by LeeJaeWon on 2017-05-26.
 */

public class riderlist_item {
    private String name;
    private String money;
    private String point;
    private String riderid;
    private String no;
    private String comcount;
//    private String face;
    private ImageView gisaimage;


    public riderlist_item(String riderid,String money,String no,String name,String comcount,String point) {  // String face 빠짐
        this.riderid=riderid;
        this.money=money;
        this.no=no;
        this.name=name;
        this.point=point;
        this.comcount=comcount;
//        this.face=face;
//        this.gisaimage;
    }

    public String getriderid(){return riderid;}
    public String getMoney(){return money;}
    public String getNo(){return no;}
    public String getName(){return name;}
    public String getComcount(){return  comcount;}
    public String getPoint(){return point;}
//    public String getface(){return face;}
//    public ImageView gisa


}
