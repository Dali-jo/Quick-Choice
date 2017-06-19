package com.example.leejaewon.quickchoice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;
import com.squareup.picasso.Picasso;

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
    private String name="";
    private String finalmoney="";
    private String memo="";
    private String payment="";
    private String goodsphoto="";
    private String ridername="";
    private String distance="";

    update up;

    int count=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_location);
        Intent intent = getIntent();

        // 추가
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView)findViewById(R.id.location_call);
        TextView textView2 = (TextView)findViewById(R.id.button7);
        TextView location_rider=(TextView)findViewById(R.id.location_rider);
        TextView location_start = (TextView)findViewById(R.id.location_start);
        TextView location_dest= (TextView)findViewById(R.id.location_dest);
        TextView location_payment= (TextView)findViewById(R.id.location_payment);
        TextView location_distance= (TextView)findViewById(R.id.location_distance);
        TextView location_pay= (TextView)findViewById(R.id.location_pay);
        TextView location_memo= (TextView)findViewById(R.id.location_memo);


        ImageView location_goods=(ImageView)findViewById(R.id.location_goods);


        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        location_rider.setTypeface(typeface1);
        location_start.setTypeface(typeface1);
        location_dest.setTypeface(typeface1);
        location_payment.setTypeface(typeface1);
        location_distance.setTypeface(typeface1);
        location_pay.setTypeface(typeface1);
        location_memo.setTypeface(typeface1);

        name=intent.getStringExtra("name");
        finalmoney=intent.getStringExtra("finalmoney");
        memo=intent.getStringExtra("memo");
        payment=intent.getStringExtra("payment");
        goodsphoto=intent.getStringExtra("goodsphoto");
        distance=intent.getStringExtra("distance");

        //여기 까지


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

        try {
            String s= null;
            s = customTask2.execute(driverID).get();
            Log.i("기사좌표",s);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //추가
        CustomTask1 customTask1=new CustomTask1();
        try {
            name = customTask1.execute(driverID).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        location_distance.setText(distance);
        location_rider.setText(name);
        location_start.setText(start);
        location_dest.setText(desti);
//        location_payment.setText(payment);
        if(payment.equals("0")){
            location_payment.setText("카드");
        }
        if(payment.equals("1")){
            location_payment.setText("현금");
        }
//        location_distance.setText();
        location_pay.setText(finalmoney);
        location_memo.setText(memo);





        Toast.makeText(this,intent.getExtras().toString(), Toast.LENGTH_LONG).show();


        Picasso.with(getApplicationContext())
                .load("http://220.122.180.160:8080/picture/goods/"+goodsphoto+".jpg")
//                .load("http://220.122.180.160:8080/picture/goods/"+fileAddr)


//                .placeholder(R.drawable.ic_launcher)//이미지가 존재하지 않을                                                                                                      경우 대체 이미지

//                .resize(100, 100) // 이미지 크기를 재조정하고 싶을 경우

                .fit()    // 이미지에 맞게 조절절

                .into(location_goods);
        //여기까지

        //선언
        MapView mapView11 = (MapView)findViewById(R.id.mapView);


        RelativeLayout mapView = new RelativeLayout(this);
        tMapView = new TMapView(this);




//        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.ic_menu);
//        tourMarkerItem.setIcon(bitmap);


//        mMapView.setCenterPoint(mapX,mapY , false);



        //세팅
        tMapView.setSKPMapApiKey("8c430cbd-0174-365a-879a-98909c5e6f73"); //발급받은 api 키


        tMapView.setCompassMode(false);
        tMapView.setIconVisibility(false);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setTrackingMode(false);
        tMapView.setSightVisible(true);





        TMapMarkerItem tourMarkerItem = new TMapMarkerItem();
        TMapPoint start = new TMapPoint(startlati,startlongi);
//        TMapPoint desti = new TMapPoint(destinationlongi,destinationlati);
        tourMarkerItem.setTMapPoint(start);
//        tourMarkerItem.setTMapPoint(desti);
        Bitmap a = BitmapFactory.decodeResource(this.getResources(),R.drawable.startposition);
        tourMarkerItem.setIcon(a);
        tourMarkerItem.setVisible(TMapMarkerItem.VISIBLE);
//        tMapView.addMarkerItem("aa",tourMarkerItem);

        TMapMarkerItem tourMarkerItem2 = new TMapMarkerItem();
//        TMapPoint start = new TMapPoint(startlati,startlongi);
        TMapPoint desti = new TMapPoint(destinationlati,destinationlongi);
//        tourMarkerItem.setTMapPoint(start);
        tourMarkerItem2.setTMapPoint(desti);
        Bitmap b = BitmapFactory.decodeResource(this.getResources(),R.drawable.destination);
        tourMarkerItem2.setIcon(b);
        tourMarkerItem2.setVisible(TMapMarkerItem.VISIBLE);
//        tMapView.addMarkerItem("aa",tourMarkerItem);



        mapView11.addView(tMapView);
        tMapView.addMarkerItem("destination",tourMarkerItem2);
        tMapView.addMarkerItem("start", tourMarkerItem);


        up = new update();
        up.start();


        Log.i("좌표",String.valueOf(startlati));
        Log.i("좌표",String.valueOf(startlongi));
        Log.i("좌표",String.valueOf(destinationlati));
        Log.i("좌표",String.valueOf(destinationlongi));


    }
    @Override
    public void onPause(){
        up.runStop();
        super.onPause();

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
            update(result);


        }


        public void update(String s) {

            int i = 0;
            StringTokenizer tokenizer = new StringTokenizer(s, ",");
            while (tokenizer.hasMoreTokens()) {
                if (i == 0) {
                    riderrati = tokenizer.nextToken();
                    i++;
                } else {
                    riderlongi = tokenizer.nextToken();
                }

            }
            Log.i("받은좌표:", riderlongi + " " + riderrati);
            if (riderlongi != "" || riderrati != "") {
                if(riderlongi=="null"||riderrati=="null") {
                    if (riderlongi != null || riderrati != null) {
                        if (count == 0) {
                            tMapView.setCenterPoint(Double.parseDouble(riderlongi), Double.parseDouble(riderrati));
                            count++;

                            TMapMarkerItem tourMarkerItem3 = new TMapMarkerItem();
                            TMapPoint rider = new TMapPoint(Double.parseDouble(riderrati), Double.parseDouble(riderlongi));
                            tourMarkerItem3.setTMapPoint(rider);
                            Bitmap c = BitmapFactory.decodeResource(getBaseContext().getResources(),R.drawable.riderposition);
                            tourMarkerItem3.setIcon(c);
                            tourMarkerItem3.setVisible(TMapMarkerItem.VISIBLE);
                            tMapView.addMarkerItem("rider", tourMarkerItem3);

                        }

                        if (tMapView.getMarkerItemFromID("rider") != null) {
                            TMapMarkerItem a = tMapView.getMarkerItemFromID("rider");
                            TMapPoint now = new TMapPoint(Double.parseDouble(riderrati), Double.parseDouble(riderlongi));
                            a.setTMapPoint(now);
                            tMapView.refreshMap();
                        }
                        Log.i("받음2", receiveMsg);
                        Log.i("라위2", riderrati);
                        Log.i("라경2", riderlongi);
                    }
                }
            }
        }



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
