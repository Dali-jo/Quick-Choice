package com.example.leejaewon.quickchoice;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.MapView;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

/**
 * Created by LeeJaeWon on 2017-04-18.
 */

public class location extends AppCompatActivity {
    private TMapView tMapView;
    private String start;
    private String desti;
    private String driverID;
    private Double startlati=0.0;
    private Double startlongi=0.0;
    private Double destinationlati=0.0;
    private Double destinationlongi=0.0;
    private Button call;
    private String riderrati="";
    private String riderlongi="";
    update up;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_location);
        Intent intent = getIntent();
        start=intent.getStringExtra("start");
        desti=intent.getStringExtra("desti");
        driverID=intent.getStringExtra("driver");
        startlati=Double.parseDouble(intent.getStringExtra("startlati"));
        startlongi=Double.parseDouble(intent.getStringExtra("startlongi"));
        destinationlati=Double.parseDouble(intent.getStringExtra("destinationlati"));
        destinationlongi=Double.parseDouble(intent.getStringExtra("destinationlongi"));
        call=(Button) findViewById(R.id.location_call);

        calllistener calllistener=new calllistener(intent.getStringExtra("phonenumber"));
        call.setOnClickListener(calllistener);

        CustomTask2 customTask2= new CustomTask2();
        String s= null;
        try {
            s = customTask2.execute(driverID).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("기사좌표",s);



        //선언
        MapView mapView11 = (MapView)findViewById(R.id.mapView);


        RelativeLayout mapView = new RelativeLayout(this);
        tMapView = new TMapView(this);




//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_menu);
//        tourMarkerItem.setIcon(bitmap);


//        mMapView.setCenterPoint(mapX,mapY , false);



        //세팅
        tMapView.setSKPMapApiKey("f146a2c8-167d-31ec-88bf-93f167149d2a"); //발급받은 api 키


        tMapView.setCompassMode(false);
        tMapView.setIconVisibility(false);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(false);
        tMapView.setSightVisible(true);

        tMapView.setCenterPoint(Double.parseDouble(riderlongi), Double.parseDouble(riderrati));



        TMapMarkerItem tourMarkerItem = new TMapMarkerItem();
        TMapPoint start = new TMapPoint(startlati,startlongi);
//        TMapPoint desti = new TMapPoint(destinationlongi,destinationlati);
        tourMarkerItem.setTMapPoint(start);
//        tourMarkerItem.setTMapPoint(desti);
        tourMarkerItem.setVisible(TMapMarkerItem.VISIBLE);
//        tMapView.addMarkerItem("aa",tourMarkerItem);

        TMapMarkerItem tourMarkerItem2 = new TMapMarkerItem();
//        TMapPoint start = new TMapPoint(startlati,startlongi);
        TMapPoint desti = new TMapPoint(destinationlati,destinationlongi);
//        tourMarkerItem.setTMapPoint(start);
        tourMarkerItem2.setTMapPoint(desti);
        tourMarkerItem2.setVisible(TMapMarkerItem.VISIBLE);
//        tMapView.addMarkerItem("aa",tourMarkerItem);

        TMapMarkerItem tourMarkerItem3 = new TMapMarkerItem();
        TMapPoint rider = new TMapPoint(Double.parseDouble(riderrati), Double.parseDouble(riderlongi));
        tourMarkerItem3.setTMapPoint(rider);
        tourMarkerItem3.setVisible(TMapMarkerItem.VISIBLE);

        mapView11.addView(tMapView);
        tMapView.addMarkerItem("destination",tourMarkerItem2);
        tMapView.addMarkerItem("start", tourMarkerItem);
        tMapView.addMarkerItem("rider",tourMarkerItem3);

        up = new update();
        up.start();


        Log.i("좌표",String.valueOf(startlati));
        Log.i("좌표",String.valueOf(startlongi));
        Log.i("좌표",String.valueOf(destinationlati));
        Log.i("좌표",String.valueOf(destinationlongi));


    }
    @Override
    public void onPause(){
        super.onPause();
        up.runStop();
    }

    public class calllistener implements View.OnClickListener {
        String phone="tel:";
        public calllistener(String s){
            phone=phone+s;
        }
        @Override
        public void onClick(View v)
        {
            startActivity(new Intent("android.intent.action.DIAL", Uri.parse(phone)));

        }
    }


    public class update extends Thread{
        private boolean isRun=true;
        @Override
        public void run(){
            while(isRun){




            try {
                CustomTask2 cus=new CustomTask2();

                cus.execute(driverID);
                if(tMapView.getMarkerItemFromID("rider")!=null) {
                    TMapMarkerItem a = tMapView.getMarkerItemFromID("rider");
                    TMapPoint now = new TMapPoint(Double.parseDouble(riderrati), Double.parseDouble(riderlongi));
                    a.setTMapPoint(now);
                }

                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }

        }
        public void runStop(){
            isRun=false;
        }
    }

    private class CustomTask2 extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/riderlocation.jsp");
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

                    update(receiveMsg);
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


        public void update(String s){
            int i=0;
            StringTokenizer tokenizer=new StringTokenizer(receiveMsg,",");
            while(tokenizer.hasMoreTokens()){
                if(i==0){
                    riderrati=tokenizer.nextToken();
                    i++;
                } else{
                    riderlongi=tokenizer.nextToken();
                }

            }
            Log.i("받음2",receiveMsg);
            Log.i("라위2",riderrati);
            Log.i("라경2",riderlongi);
        }



    }

}
