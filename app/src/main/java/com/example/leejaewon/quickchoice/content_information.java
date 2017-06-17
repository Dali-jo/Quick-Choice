package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LeeJaeWon on 2017-04-30.
 */

public class content_information extends Fragment {
    private TextView information_name;
    private TextView information_id;
    private TextView information_phone;
    private Button information_changepw;
    private Button information_addjuso;
    private Button information_logout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.content_information,container,false);
        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        information_name = (TextView)view.findViewById(R.id.information_name);
        information_id = (TextView)view.findViewById(R.id.information_id);
        information_phone = (TextView)view.findViewById(R.id.information_phone);
        TextView textView4 = (TextView)view.findViewById(R.id.textView4);//
        TextView textView5 = (TextView)view.findViewById(R.id.textView6);//
        information_changepw = (Button)view.findViewById(R.id.information_chagepw);
        information_addjuso = (Button)view.findViewById(R.id.information_addjuso);
        information_logout = (Button)view.findViewById(R.id.information_logout);

        information_name.setTypeface(typeface1);
        information_id.setTypeface(typeface1);
        information_phone.setTypeface(typeface1);
        textView4.setTypeface(typeface1);
        textView5.setTypeface(typeface1);
        information_changepw.setTypeface(typeface1);
        information_addjuso.setTypeface(typeface1);
        information_logout.setTypeface(typeface1);
        CustomTask customTask=new CustomTask();
        customTask.execute(main.myId);

        return view;


    }

    private class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/myinfo.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&userid="+strings[0];
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

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            try {
                JSONParser parser = new JSONParser();


                JSONObject wrapObject = null;
                wrapObject = (JSONObject)parser.parse(result);
                information_name.setText((String) wrapObject.get("name")+"님");
                information_phone.setText((String) wrapObject.get("phonenumber"));
                information_id.setText(main.myId);


            } catch (ParseException e) {
                e.printStackTrace();
            }
        }



    }



}
