package com.example.leejaewon.quickchoice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by LeeJaeWon on 2017-05-26.
 */

public class rider_list extends AppCompatActivity {
    private String no;

    private RecyclerView riderlistView;
    private ArrayList<riderlist_item> riderlist_item_ArrayList;
    private riderlist_atapter riderlist_atapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_recyclerview);
        Intent intent = getIntent();
        this.no=intent.getStringExtra("no");


        riderlist_item_ArrayList=new ArrayList<>();
        riderlistView=(RecyclerView) findViewById(R.id.orderlist_recyclerview);
        riderlistView.setHasFixedSize(true);

        CustomTask customTask = new CustomTask();
        customTask.execute();

    }



    class CustomTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings){
            InputStream inputStream=null;
            String result="";
            StringBuilder sb=null;
//            Log.i("유저아이디: ",string);
            try {



                URL url = new URL("http://220.122.180.160:8080/riderlist.jsp");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

//                JSONObject jsonObject=new JSONObject();
//                JSONArray jsonArray=new JSONArray();
//
//                JSONObject jsonObject1=new JSONObject();
//
//                jsonObject1.put("userid",string);
//                jsonArray.put(jsonObject1);
//                jsonObject.put("list",jsonArray);
//
//
//                String json=jsonObject.toString();
//                httpURLConnection.setRequestProperty("Accept", "application/json");
//                httpURLConnection.setRequestProperty("Content-type", "application/json");




                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream os =httpURLConnection.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "EUC-KR"));
                writer.write("&no="+no);
                writer.flush();
                writer.close();
                os.close();

                httpURLConnection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "EUC-KR")); //캐릭터셋 설정

                sb = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    if(sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(line);
                }

                receiveArray(sb.toString());
                publishProgress();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);

            riderlist_atapter = new riderlist_atapter(getBaseContext(),riderlist_item_ArrayList,no);
            riderlistView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

            riderlistView.setAdapter(riderlist_atapter);


        }
    }

    public  void receiveArray(String dataObject){



        try {
            JSONParser parser = new JSONParser();


            JSONObject wrapObject = null;
            wrapObject = (JSONObject)parser.parse(dataObject);

//            wrapObject.get("list");


            JSONArray jsonArray=  (JSONArray)wrapObject.get("list");
//            JSONArray jsonArray=new JSONArray(wrapObject.toString());
            for(int i=0;i<jsonArray.size();i++){
                JSONObject dataObject1 = (JSONObject)jsonArray.get(i);
                riderlist_item item = new riderlist_item((String)dataObject1.get("riderid"),(String)dataObject1.get("tendermoney"),(String)dataObject1.get(no),(String)dataObject1.get("name"),(String)dataObject1.get("comcount"),(String)dataObject1.get("point"));


                riderlist_item_ArrayList.add(item);
                Log.i("아이템 등록" , riderlist_item_ArrayList.toString());
            }








        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
