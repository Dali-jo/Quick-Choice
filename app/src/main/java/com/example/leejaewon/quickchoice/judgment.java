package com.example.leejaewon.quickchoice;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by LeeJaeWon on 2017-06-05.
 */

public class judgment extends AppCompatActivity{
    private int point;
    private EditText memo;
    private RatingBar ratingBar;
    private TextView start;
    private TextView desti;
    private TextView money;
    private TextView time;
    private TextView distance;
    private String userID;
    private String riderID;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_judgment);
        Intent intent = getIntent();
        riderID=intent.getStringExtra("driver");
        userID=main.getUserId();
        Log.i("유저아이디",userID);

        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView)findViewById(R.id.judgment_tv_start);
        TextView textView2 = (TextView)findViewById(R.id.judgment_tv_desti);
        TextView textView3 = (TextView)findViewById(R.id.judgment_tv_distance);
        TextView textView4 = (TextView)findViewById(R.id.judgment_tv_money);
        TextView textView5 = (TextView)findViewById(R.id.judgment_tv_time);
        TextView textView6 = (TextView)findViewById(R.id.textView9);
        TextView textView7 = (TextView)findViewById(R.id.textView20);
        memo = (EditText) findViewById(R.id.judgment_memo);
        money = (TextView)findViewById(R.id.judgment_money);
        ok = (Button) findViewById(R.id.judgment_ok);
        time = (TextView)findViewById(R.id.judgment_time);
        start = (TextView)findViewById(R.id.judgment_start);
        distance = (TextView)findViewById(R.id.judgment_distance);
        desti = (TextView)findViewById(R.id.judgment_desti);


        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface1);
        textView4.setTypeface(typeface1);
        textView5.setTypeface(typeface1);
        textView6.setTypeface(typeface1);
        textView7.setTypeface(typeface1);
        memo.setTypeface(typeface1);
        ok.setTypeface(typeface1);
        money.setTypeface(typeface1);
        time.setTypeface(typeface1);
        start.setTypeface(typeface1);
        distance.setTypeface(typeface1);
        desti.setTypeface(typeface1);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setStepSize(1);

        start.setText(intent.getStringExtra("start"));
        desti.setText(intent.getStringExtra("desti"));
        distance.setText(intent.getStringExtra("distance"));
        money.setText(intent.getStringExtra("finalmoney"));

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                point = (int)rating;
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                memo.getText().toString();

                try {

                    CustomTask customTask = new CustomTask();
                    customTask.execute(riderID,userID,String.valueOf(point),memo.getText().toString()).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });



    }




    private class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/jugement.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&riderid="+strings[0]+"&userid="+strings[1]+"&point="+strings[2]+"&memo="+strings[3];
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
