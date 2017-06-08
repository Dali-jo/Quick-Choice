package com.example.leejaewon.quickchoice;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LeeJaeWon on 2017-04-19.
 */

public class join extends AppCompatActivity{
    EditText id;
    EditText pw;
    EditText pw_check;
    EditText name;
    EditText phonNumber;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_join);

        id=(EditText) findViewById(R.id.join_id);
        pw=(EditText) findViewById(R.id.join_pw);
        pw_check=(EditText) findViewById(R.id.join_pw_check);
        name=(EditText) findViewById(R.id.join_name);
        phonNumber=(EditText) findViewById(R.id.join_phone);

        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView)findViewById(R.id.join_id);
        TextView textView2 = (TextView)findViewById(R.id.join_pw);
        TextView textView3 = (TextView)findViewById(R.id.join_pw_check);
        TextView textView4 = (TextView)findViewById(R.id.join_phone);
        TextView textView5 = (TextView)findViewById(R.id.join_ok);
        TextView textView6 = (TextView)findViewById(R.id.join_cancle);
        TextView textView7 = (TextView)findViewById(R.id.join_name);
        TextView textView8 = (TextView)findViewById(R.id.join_1);
        TextView textView9 = (TextView)findViewById(R.id.join_2);
        TextView textView10 = (TextView)findViewById(R.id.join_3);

        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface1);
        textView4.setTypeface(typeface1);
        textView5.setTypeface(typeface1);
        textView6.setTypeface(typeface1);
        textView7.setTypeface(typeface1);
        textView8.setTypeface(typeface1);
        textView9.setTypeface(typeface1);
        textView10.setTypeface(typeface1);
    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/join.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&id="+strings[0]+"&pw="+strings[1]+"&name="+strings[2]+"&phon="+strings[3];
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

//    TextWatcher watcher=new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            if(id.isFocusable()){
//                login.CustomTask1 task= new login.CustomTask1();
//
//
//            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    }



    public void join_Button(View v){

        try {

            String result;
            CustomTask task = new CustomTask();
            result = task.execute(id.getText().toString(),pw.getText().toString(),name.getText().toString(),phonNumber.getText().toString()).get();
            Log.i("리턴 값",result);
            Toast.makeText(this,result, Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }
    }

    public void join_Cancle(View v){
        finish();
    }
}
