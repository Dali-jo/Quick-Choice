package com.example.leejaewon.quickchoice;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by LeeJaeWon on 2017-05-14.
 */

public class orderlist_adapter extends RecyclerView.Adapter<orderlist_viewholder>{
    private Context mContext;
    private ArrayList<orderlist_item> orderlist_items;
    private String driverID;

    public orderlist_adapter(Context context,ArrayList<orderlist_item> orderlist_items){
        mContext=context;
        this.orderlist_items=orderlist_items;
    }

    @Override
    public orderlist_viewholder onCreateViewHolder(ViewGroup parent,int viewType){
        View baseView =View.inflate(mContext,R.layout.content_orderlist,null);
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_orderlist,parent,true);

        orderlist_viewholder orderlist_viewholder = new orderlist_viewholder(baseView);
        return orderlist_viewholder;
    }

    @Override
    public void onBindViewHolder(final orderlist_viewholder holder, int position){
        orderlist_item item = orderlist_items.get(position);
        driverID=item.getDriver();
        holder.item_start.setText("출발지:"+item.getStart());
        holder.item_desti.setText("도착지:"+item.getDesti());
        switch (item.getState()){
            case "0":
                holder.item_state.setText("입찰중");
                holder.item_driver.setText("X");
                holder.item_money.setText("희망요금"+item.getMoney());
                holder.itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Log.i("aaaa","aaaaaaaa");
                        Intent intent = new Intent(mContext,rider_list.class);
                        intent.putExtra("no",holder.item_no);
                        mContext.startActivity(intent);
                    }
                });
                break;
            case "1":

                try {
                    CustomTask1 customTask1=new CustomTask1();
                    String s = customTask1.execute(item.getDriver()).get();
                    holder.item_state.setText("배송중");
                    holder.item_driver.setText("담당기사: "+s);
                    holder.item_money.setText("요금: "+item.getFinalmoney());
                    holder.itemView.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            Intent intent = new Intent(mContext,location.class);
                            intent.putExtra("start",holder.item_start.toString());
                            intent.putExtra("destiy",holder.item_desti.toString());
                            intent.putExtra("driver",driverID);
                            mContext.startActivity(intent);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                break;
            case "2":
                try {
                    CustomTask1 customTask1=new CustomTask1();
                    String s = customTask1.execute(item.getDriver()).get();
                    holder.item_state.setText("배송완료");
                    holder.item_driver.setText("담당기사: "+s);
                    holder.item_money.setText("요금: "+item.getFinalmoney());
                    holder.itemView.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){

                            Intent intent = new Intent(mContext,judgment.class);
                            mContext.startActivity(intent);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }



        holder.item_no=item.getNo();


    }

    @Override
    public int getItemCount(){
        return orderlist_items.size();
    }

    class CustomTask1 extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/serchname.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&riderid="+strings[0];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }



    }
}
