package com.example.leejaewon.quickchoice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by LeeJaeWon on 2017-05-26.
 */

public class riderlist_viewholder extends RecyclerView.ViewHolder {
    public TextView rider_name;
    public TextView money;

    public String riderid;
    public String no;


    public riderlist_viewholder(View itemview){
        super(itemview);

        rider_name=(TextView)itemview.findViewById(R.id.tv_gisaname);
        money=(TextView)itemview.findViewById(R.id.tv_gisapay);



    }
}
