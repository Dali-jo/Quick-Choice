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
    private String startlati;
    private String startlongi;
    private String destinationlati;
    private String destinationlongi;
    private String phonenumber;

    public orderlist_item(String no, String start, String desti, String driver, String money,String state,String finalmoney,String startlati,String startlongi,String destinationlati,String destinationlongi,String phonenumber) {
        this.no=no;
        this.start=start;
        this.desti=desti;
        this.driver=driver;
        this.money=money;
        this.state=state;
        this.finalmoney=finalmoney;
        this.startlati=startlati;
        this.startlongi=startlongi;
        this.destinationlati=destinationlati;
        this.destinationlongi=destinationlongi;
        this.phonenumber=phonenumber;
        //생성자 추가 안했음
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
    public String getStartlati(){return startlati;}
    public String getStartlongi(){return startlongi;}
    public String getDestinationlati(){return destinationlati;}
    public String getDestinationlongi(){return destinationlongi;}
    public String getPhonenumber(){return phonenumber;}



}
