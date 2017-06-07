package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by LeeJaeWon on 2017-05-14.
 */

public class content_orderlist extends Fragment {
    private RecyclerView orderlistView;
    private ArrayList<orderlist_item> orderlist_item_ArrayList;
    private orderlist_adapter orderlist_adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.content_recyclerview,container,false);

        orderlist_item_ArrayList=new ArrayList<>();
        orderlistView=(RecyclerView) view.findViewById(R.id.orderlist_recyclerview);
        orderlistView.setHasFixedSize(true);

        CustomTask customTask = new CustomTask();
        customTask.execute();
//        for(int i=0;i<30;i++){
//            orderlist_item item = new orderlist_item("user","5","3","5000","http","asd");
//
//            orderlist_item_ArrayList.add(i,item);
//        }




//        getFragmentManager().beginTransaction().commit();
//        this.getFragmentManager().beginTransaction().commit();
//        Log.i("item" , orderlist_item_ArrayList.toString());


        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

//        receiveArray(customTask.getrecevie());


//        String st = String.valueOf(customTask.execute());
//        Log.i("통신 결과",st);


//        Toast.makeText(getActivity(),customTask.getrecevie(),Toast.LENGTH_LONG).show();
//        customTask.receiveMsg;


    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                String str;
//                URL url = new URL("http://220.122.180.160:8080/orderlist.jsp");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                conn.setRequestMethod("POST");
//                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
//                sendMsg = "&userid="+strings[0];
//                osw.write(sendMsg);
//                osw.flush();
//                if(conn.getResponseCode() == conn.HTTP_OK) {
//                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
//                    BufferedReader reader = new BufferedReader(tmp);
//                    StringBuffer buffer = new StringBuffer();
//                    while ((str = reader.readLine()) != null) {
//                        buffer.append(str);
//                    }
//                    receiveMsg = buffer.toString();
//
//                } else {
//                    Log.i("통신 결과", conn.getResponseCode()+"에러");
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return receiveMsg;
//        }


        @Override
        protected String doInBackground(String... strings){
            InputStream inputStream=null;
            String result="";
            String string =((main)getActivity()).userid;
            StringBuilder sb=null;
            Log.i("유저아이디: ",string);
            try {



                URL url = new URL("http://220.122.180.160:8080/orderlist.jsp");
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
                writer.write("userid="+string);
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
//                receiveMsg=sb.toString();

//                receiveArray(sb.toString());


//                Log.i("받음 : ", sb.toString());


//                os.write(json.getBytes("euc-kr"));
//                os.flush();
//                inputStream=httpURLConnection.getInputStream();
//
//                Log.i("dd:", String.valueOf(inputStream));
//                result=convertInputStreamToString(inputStream);
//                result= inputStream.toString();

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

            orderlist_adapter = new orderlist_adapter(getActivity(),orderlist_item_ArrayList);
            orderlistView.setLayoutManager(new LinearLayoutManager(getActivity()));

            orderlistView.setAdapter(orderlist_adapter);

        }

        public String getrecevie(){
            return receiveMsg;
        }

        private  String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();

            return result;

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
                orderlist_item  item = new orderlist_item((String)dataObject1.get("no"),(String)dataObject1.get("startadd"),(String)dataObject1.get("destinationadd"),(String)dataObject1.get("riderid"),(String)dataObject1.get("hopemoney"),(String)dataObject1.get("state"),(String)dataObject1.get("finalmoney"));


                orderlist_item_ArrayList.add(item);
                Log.i("아이템 등록" , orderlist_item_ArrayList.toString());
            }








        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
