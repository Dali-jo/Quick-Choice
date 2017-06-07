package com.example.leejaewon.quickchoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.MapView;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

/**
 * Created by LeeJaeWon on 2017-04-18.
 */

public class location extends AppCompatActivity {
    private TMapView tMapView;
    private String start;
    private String desti;
    private String driverID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_location);
        Intent intent = getIntent();
        start=intent.getStringExtra("start");
        desti=intent.getStringExtra("desti");
        driverID=intent.getStringExtra("driverID");



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

        tMapView.setCenterPoint(128.623125, 35.896537);

        TMapMarkerItem tourMarkerItem = new TMapMarkerItem();
        TMapPoint tpoint = new TMapPoint(35.896537,128.623125 );
        tourMarkerItem.setTMapPoint(tpoint);
        tourMarkerItem.setVisible(TMapMarkerItem.VISIBLE);
        tMapView.addMarkerItem("aa",tourMarkerItem);

        mapView11.addView(tMapView);

        tMapView.addMarkerItem("tourMarker", tourMarkerItem);




    }



}
