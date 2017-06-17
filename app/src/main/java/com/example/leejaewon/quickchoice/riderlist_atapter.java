package com.example.leejaewon.quickchoice;

import android.content.Context;
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
 * Created by LeeJaeWon on 2017-05-26.
 */

public class riderlist_atapter extends RecyclerView.Adapter<riderlist_viewholder>{
        private String no;
        private String finalmoney;
        private Context mContext;
        private ArrayList<riderlist_item> riderlist_items;

        public riderlist_atapter(Context context, ArrayList<riderlist_item> riderlist_items,String no){
            mContext=context;
            this.riderlist_items = riderlist_items;
            this.no=no;
        }

        @Override
        public riderlist_viewholder onCreateViewHolder(ViewGroup parent, int viewType){
            View baseView =View.inflate(mContext,R.layout.rider_list,null);
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_orderlist,parent,true);

            riderlist_viewholder riderlist_viewholder = new riderlist_viewholder(baseView);
            return riderlist_viewholder;
        }

        @Override
        public void onBindViewHolder(final riderlist_viewholder holder, int position){
            riderlist_item item = riderlist_items.get(position);
            holder.rider_name.setText(item.getName()+" 기사님");
            holder.money.setText("가격 : "+item.getMoney()+"원");
            finalmoney=item.getMoney();
            holder.riderid=item.getriderid();
            holder.comcount.setText("배송횟수 : "+item.getComcount());
            holder.point.setText("평점 : "+item.getPoint());
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    String s;

                    CustomTask customTask=new CustomTask();
                    try {
                      s=  customTask.execute(holder.riderid,no,finalmoney).get();
                        Log.i("입찰",s);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                }
            });
        }

        @Override
        public int getItemCount(){
            return riderlist_items.size();
        }


    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/riderchoice.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&riderid="+strings[0]+"&no="+strings[1]+"&finalmoney="+strings[2];
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
