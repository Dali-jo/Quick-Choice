package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.List;

/**
 * Created by LeeJaeWon on 2017-04-30.
 */

public class content_order_sub1 extends Fragment {
    Button btn = null;
    ImageView iv = null;

    int category =0;

    String start_addr;
    String desty_addr;

    EditText start;
    EditText desty;
    ImageButton bt1;
    ImageButton bt2;


    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.content_order_sub1,container,false);

        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView) view.findViewById(R.id.textView3);
        TextView textView2 = (TextView) view.findViewById(R.id.textView4);
        TextView textView3 = (TextView) view.findViewById(R.id.radio_small);
        TextView textView4 = (TextView) view.findViewById(R.id.radio_flower);
        TextView textView5 = (TextView) view.findViewById(R.id.radio_big);
        TextView textView6 = (TextView) view.findViewById(R.id.radio_ex);
        TextView textView7 = (TextView) view.findViewById(R.id.textView6);
        TextView textView8 = (TextView) view.findViewById(R.id.textView7);
        TextView textView9 = (TextView) view.findViewById(R.id.button4);
        TextView textView10 = (TextView) view.findViewById(R.id.radio_paper);
        TextView textView11 = (TextView) view.findViewById(R.id.radio_food);
        TextView textView12 = (TextView) view.findViewById(R.id.put_pic);

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
        textView11.setTypeface(typeface1);
        textView12.setTypeface(typeface1);


        start=(EditText) view.findViewById(R.id.order_start);
        desty=(EditText) view.findViewById(R.id.order_desti);
        bt1=(ImageButton) view.findViewById(R.id.search1);
        bt2=(ImageButton) view.findViewById(R.id.search2);
        listener lis = new listener();
        bt1.setOnClickListener(lis);
        bt2.setOnClickListener(lis);

        rg1=(RadioGroup) view.findViewById(R.id.rag1);
        rg2=(RadioGroup) view.findViewById(R.id.rag2);
        rg3=(RadioGroup) view.findViewById(R.id.rag3);
        rg1.setOnCheckedChangeListener(rglis1);
        rg2.setOnCheckedChangeListener(rglis2);
        rg3.setOnCheckedChangeListener(rglis3);

        btn = (Button) view.findViewById(R.id.put_pic);
        iv = (ImageView)view.findViewById(R.id.imagetest);

        imagelistener imag_lis = new imagelistener();
        btn.setOnClickListener(imag_lis);

        return view;

    }
    @Override
    public void onPause(){
        super.onPause();
        ((main)getActivity()).start =start.getText().toString();
        ((main)getActivity()).desti =desty.getText().toString();
        ((main)getActivity()).category = this.category;

    }


    private RadioGroup.OnCheckedChangeListener rglis1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if(checkedId!=-1){
                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(rglis2);
                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(rglis3);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener rglis2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if(checkedId!=-1){
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(rglis1);
                rg3.setOnCheckedChangeListener(null);
                rg3.clearCheck();
                rg3.setOnCheckedChangeListener(rglis3);
            }
        }
    };
    private RadioGroup.OnCheckedChangeListener rglis3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            if(checkedId!=-1){
                rg1.setOnCheckedChangeListener(null);
                rg1.clearCheck();
                rg1.setOnCheckedChangeListener(rglis1);
                rg2.setOnCheckedChangeListener(null);
                rg2.clearCheck();
                rg2.setOnCheckedChangeListener(rglis2);
            }
        }
    };



    class listener implements View.OnClickListener{
        public void onClick(View v){
            int i=0;
            switch (v.getId()){
                case R.id.search1:
                    i=0;
                    search_add(v,i);
                    break;
                case R.id.search2:
                    i=1;
                    search_add(v,i);
                    break;
                case R.id.radio_paper:
                    category=0;
                    break;
                case R.id.radio_small:
                    category=1;
                    break;
                case R.id.radio_big:
                    category=2;
                    break;
                case R.id.radio_food:
                    category=3;
                    break;
                case R.id.radio_flower:
                    category=4;
                    break;
                case R.id.radio_ex:
                    category=5;
                    break;

            }


        }
    }

        @Override
        public void onStart() {
        super.onStart();

//        Bundle args = this.getArguments();

//            if(this.getArguments().getBundle("juso")!= null){
//                start.setText(this.getArguments().getBundle("juso").getString("juso"));
//            }
//        if(args!=null){
//            String str = args.toString();
//            Toast.makeText(getActivity(),str+"받음 프래그먼트",Toast.LENGTH_LONG).show();
//            start.setText(args.getString("juso"));


//        }
    }

//    public void aa(){
//        ((main)getActivity())


    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("start",start.getText().toString());
        outState.putString("start",desty.getText().toString());
    }
    public void setText(String str){
        start.setText(str);
    }

    public void search_add(View v,int i) {
        Intent intent = new Intent(getActivity(),address_search.class);
        startActivityForResult(intent,i);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> list=null;
        if(requestCode==0){
            if(resultCode==1){
                start.setText(data.getStringExtra("juso"));

                try {



                        list=geocoder.getFromLocationName(data.getStringExtra("juso"),1);
                        if (list != null) {

                            Address addr = list.get(0);
                            ((main) getActivity()).start_Latitude = String.valueOf(addr.getLatitude());
                            ((main) getActivity()).start_Longitude = String.valueOf(addr.getLongitude());
                        }


                } catch (IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(getActivity(), data.getStringExtra("juso")+"fragment + la:"+((main)getActivity()).start_Latitude+"long"+((main)getActivity()).start_Longitude, Toast.LENGTH_SHORT).show();

            }
        }
        if(requestCode==1){
            if(resultCode==1){
                desty.setText(data.getStringExtra("juso"));

                try {
                    list = geocoder.getFromLocationName(data.getStringExtra("juso"), 1);
                    if (list != null) {
                        Address addr = list.get(0);
                        ((main) getActivity()).desti_Latitude = String.valueOf(addr.getLatitude());
                        ((main) getActivity()).desti_Longitude = String.valueOf(addr.getLongitude());

                        TMapView tMapView=new TMapView(getActivity());
                        tMapView.setSKPMapApiKey("8c430cbd-0174-365a-879a-98909c5e6f73");


                        TMapData tMapData=new TMapData();
                        TMapPoint start = new TMapPoint(Double.valueOf(((main) getActivity()).start_Latitude),Double.valueOf(((main) getActivity()).start_Longitude));
                        TMapPoint desti = new TMapPoint(Double.valueOf(((main) getActivity()).desti_Latitude),Double.valueOf(((main) getActivity()).desti_Longitude));
                        tMapData.findPathDataAll(start,desti,new TMapData.FindPathDataAllListenerCallback(){
                            @Override
                            public void onFindPathDataAll(Document document){
                                XMLDOMParser parser = new XMLDOMParser();
                                Document doc = document;
                                NodeList nodeList = doc.getElementsByTagName("kml");
                                for (int i = 0; i < nodeList.getLength(); i++) {
                                    Element e = (Element) nodeList.item(i);
                                    ((main) getActivity()).totaldistance = parser.getValue(e, "tmap:totalDistance");
                                    int meter = Integer.valueOf(((main) getActivity()).totaldistance);
                                    int integer=meter/1000;
                                    int prime = meter%1000;
                                    ((main) getActivity()).distance = "    "+integer+"."+prime+"Km";
                                    Log.i("거리",((main) getActivity()).distance);


                                    ((main) getActivity()).totaltime = parser.getValue(e,"tmap:totalTime");
                                    int minute = Integer.valueOf(((main) getActivity()).totaltime)/60;
                                    //분으로 변환한 나머지가 변환된 초
                                    int chsecond = Integer.valueOf(((main) getActivity()).totaltime) - minute*60;
                                    //분을 60으로 나누면 시간
                                    int hour = minute/60;
                                    //시간으로 변환한 나머지가 변환된 분
                                    minute = minute - hour*60;
                                    ((main) getActivity()).time=hour+":"+minute;
                                    Log.i("시간",((main) getActivity()).time);

                                }
                            }
                        });

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(getActivity(), data.getStringExtra("juso")+"fragment + la:"+((main)getActivity()).desti_Latitude+"long"+((main)getActivity()).desti_Longitude, Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==3){
            try{
                Bitmap profileBitmap=null;
                profileBitmap = (Bitmap)data.getExtras().get("data");
                iv.setImageBitmap(profileBitmap);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);

            } catch(Exception e){
                return;
            }
        }
    }


    public class imagelistener implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,3);
        }
    }




}
