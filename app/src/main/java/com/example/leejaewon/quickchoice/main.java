package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LeeJaeWon on 2017-04-14.
 */



public class main extends AppCompatActivity {
    public EditText order_start;
    public EditText order_desti;
    public EditText order_hopemoney;
    public RadioButton order_paytype;
    public EditText order_pickuptime;
    public CheckBox order_fast;
    public EditText order_memo;

    public String start;
    public String desti;
    public String hopemoney;
    public String pickup;
    public String memo ;
    public int paytype;
    public int fast;
    public String userid;
    public int  category;
    public int state = 0;





    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private Button btnShowNavigationDrawer;

    public Fragment fr_main = new content_main();
    public Fragment fr_order1 = new content_order_sub1();
    public Fragment fr_order2 = new content_order_sub2();
    public Fragment fr_order3 = new content_order_sub3();
    public Fragment fr_service = new content_service();
    public Fragment fr_setting = new content_setting();
    public Fragment fr_info = new content_information();
    public Fragment fr_orderlist = new content_orderlist();

    public FragmentManager fm;

    public FragmentTransaction ft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_test);
        Intent intent = this.getIntent();
        userid=intent.getStringExtra("id");

        toolbar = (Toolbar) findViewById(R.id.toolbarInclude);
        setSupportActionBar(toolbar);
        btnShowNavigationDrawer = (Button) toolbar.findViewById(R.id.btnShowNavigationDrawer);
        btnShowNavigationDrawer.setOnClickListener(onClickListener);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        setUpDrawerContent(navigationView);

        if(findViewById(R.id.fragment_main) !=null){
            if(savedInstanceState != null) {
                return;
            }
            content_main fr_main = new content_main();
            fr_main.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(R.id.fragment_main,fr_main).commit();
        }

         order_start = (EditText) findViewById(R.id.order_start);
         order_desti = (EditText) findViewById(R.id.order_desti);
         order_hopemoney = (EditText) findViewById(R.id.order_hopemoney);
         order_pickuptime = (EditText) findViewById(R.id.order_pickuptime);
         order_memo= (EditText) findViewById(R.id.order_memo);
        order_paytype=(RadioButton) findViewById(R.id.radio_cash);
        order_fast=(CheckBox) findViewById(R.id.order_fast);

    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnShowNavigationDrawer:
                    drawerLayout.openDrawer(GravityCompat.START);
                    break;
            }
        }
    };
    private void setUpDrawerContent(final NavigationView navigationView){

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            Fragment fr;
//            FragmentManager fm;
//            FragmentTransaction ft;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.first_navigation_order:
//                        fr= new content_order_sub1();
                        fm=getFragmentManager();
                        ft= fm.beginTransaction();
                        ft.replace(R.id.fragment_main,fr_order1);
                        ft.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.second_navigation_orderlist:
                        fm=getFragmentManager();
                        ft= fm.beginTransaction();
                        ft.replace(R.id.fragment_main,fr_orderlist);
                        ft.commit();
                        drawerLayout.closeDrawer(navigationView);

                        break;

                    case R.id.third_navigation_information:
//                        fr= new content_information();
                        fm=getFragmentManager();
                        ft= fm.beginTransaction();
                        ft.replace(R.id.fragment_main,fr_info);
                        ft.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;

                    case R.id.forth_navigation_service:
//                        fr= new content_service();
                        fm=getFragmentManager();
                        ft= fm.beginTransaction();
                        ft.replace(R.id.fragment_main,fr_service);
                        ft.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;

                    case R.id.fifth_navigation_setting:
//                        fr= new content_setting();
                        fm=getFragmentManager();
                        ft= fm.beginTransaction();
                        ft.replace(R.id.fragment_main,fr_setting);
                        ft.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                }

                return false;
            }
        });
    }



    public void goJoin(View v) {
        setContentView(R.layout.content_join);
    }
    public void gologin(View v) {
        setContentView(R.layout.content_login);
    }
    public void goMain(View v) {

    }

    public void goOrderSub1(View v){
//        Fragment fr;
//        FragmentManager fm;
//        FragmentTransaction ft;
//
//        fr= new content_order_sub1();
        fm=getFragmentManager();
        ft= fm.beginTransaction();
        ft.replace(R.id.fragment_main,fr_order1);
        ft.commit();
    }

    public void goOrderSub2(View v){
//        start=order_start.getText().toString();
//        desti=order_desti.getText().toString();

//        Fragment fr;
//        FragmentManager fm;
//        FragmentTransaction ft;
//
//        fr= new content_order_sub2();
        fm=getFragmentManager();
        ft= fm.beginTransaction();
        ft.replace(R.id.fragment_main,fr_order2);
        ft.commit();



    }

    public void goOrderSub3(View v){

//        hopemoney=order_hopemoney.getText().toString();
//        if(order_paytype.isChecked()){
//            paytype="0";
//        } else{
//            paytype="1";
//        }


//        Fragment fr;
//        FragmentManager fm;
//        FragmentTransaction ft;
//
//        fr= new content_order_sub3();
        fm=getFragmentManager();
        ft= fm.beginTransaction();
        ft.replace(R.id.fragment_main,fr_order3);
        ft.commit();



    }

    public void location_search(View view){
        Uri uri = Uri.parse("http://maps.google.com/maps?q="+"35.896474"+","+"128.622062");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    public void home(View view){
//        pickup=order_pickuptime.getText().toString();
//        memo=order_memo.getText().toString();
//        if(order_fast.isChecked()){
//            fast="1";
//        } else{
//            fast="0";
//        }
        fm=getFragmentManager();
        ft= fm.beginTransaction();
        ft.replace(R.id.fragment_main,fr_main);
        ft.commit();



        try {
            String result;
            main.CustomTask task = new main.CustomTask();
            result = task.execute(start,desti,hopemoney,pickup,memo,String.valueOf(paytype),String.valueOf(fast),userid,String.valueOf(category)).get();
            Log.i("리턴 값",result);
            Toast.makeText(this,result, Toast.LENGTH_LONG).show();
        } catch(Exception e){

        }

        Toast.makeText(this,""+start+desti+"돈"+hopemoney+"픽업시간"+pickup+"메모"+memo+"페이타입"+paytype+"급송"+fast+"유저"+userid+"카테고리"+category,Toast.LENGTH_LONG).show();


//        Fragment fr;
//        FragmentManager fm;
//        FragmentTransaction ft;
//
//        fr= new content_main();





    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://220.122.180.160:8080/order.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&start="+strings[0]+"&desti="+strings[1]+"&hopemoney="+strings[2]+"&pickup="+strings[3]+"&memo="+strings[4]+"&paytype="+strings[5]+"&fast="+strings[6]+"&userid="+strings[7]+"&category="+strings[8];
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){


        if(requestCode==1){
            if(resultCode==2){
//                order_start.setText(data.getStringExtra("juso"));
//                Toast.makeText(getBaseContext(),data.getStringExtra("juso")+"받음",Toast.LENGTH_LONG).show();
//                Fragment fm = new content_order_sub1();
//                Bundle bundle =new Bundle();
//                bundle.putString("juso",data.getStringExtra("juso"));
//                fr_order1.setArguments(bundle);
//
//                Toast.makeText(this,fr_order1.getArguments().toString(),Toast.LENGTH_LONG).show();
//
//
//                getFragmentManager().beginTransaction().replace(R.id.fragment_main,fr_order1).commit();

            }
        }
    }

}
