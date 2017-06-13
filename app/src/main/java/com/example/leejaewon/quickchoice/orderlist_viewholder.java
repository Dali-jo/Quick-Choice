package com.example.leejaewon.quickchoice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by LeeJaeWon on 2017-05-14.
 */

public class orderlist_viewholder extends RecyclerView.ViewHolder {
    public TextView item_start;
    public TextView item_desti;
    public TextView item_state;
    public TextView item_driver;
    public TextView item_money;

    public String item_no;

    public orderlist_viewholder(View itemview){
        super(itemview);

        item_start=(TextView) itemview.findViewById(R.id.list_item_start);
        item_desti=(TextView)itemview.findViewById(R.id.list_item_desti);
        item_state=(TextView)itemview.findViewById(R.id.list_item_state);
        item_driver=(TextView)itemview.findViewById(R.id.list_item_driver);
        item_money=(TextView)itemview.findViewById(R.id.list_item_money);
        //
    }

}
