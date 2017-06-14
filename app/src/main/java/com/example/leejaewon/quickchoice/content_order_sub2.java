package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by LeeJaeWon on 2017-05-01.
 */

public class content_order_sub2 extends Fragment {

    String hope;
    int pay;

    EditText hope_money;
    RadioButton bt_cash;
    RadioButton bt_card;

    TextView tv_quote;
    TextView tv_distance;
    TextView tv_time;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
         View view =inflater.inflate(R.layout.content_order_sub2,container,false);

        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView) view.findViewById(R.id.textView6);
        TextView textView2 = (TextView) view.findViewById(R.id.order_distance);
        TextView textView3 = (TextView) view.findViewById(R.id.order_quote);
        TextView textView4 = (TextView) view.findViewById(R.id.order_time);
        TextView textView5 = (TextView) view.findViewById(R.id.order_hopemoney);
        TextView textView6 = (TextView) view.findViewById(R.id.textView7);
        TextView textView7 = (TextView) view.findViewById(R.id.textView);
        TextView textView8 = (TextView) view.findViewById(R.id.textView2);
        TextView textView9 = (TextView) view.findViewById(R.id.textView8);
        TextView textView10 = (TextView) view.findViewById(R.id.radio_card);
        TextView textView11 = (TextView) view.findViewById(R.id.radio_cash);
        TextView textView12 = (TextView) view.findViewById(R.id.button3);
        TextView textView13 = (TextView) view.findViewById(R.id.button4);

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
        textView13.setTypeface(typeface1);

        hope_money=(EditText) view.findViewById(R.id.order_hopemoney);

        bt_card=(RadioButton) view.findViewById(R.id.radio_card);
        bt_cash=(RadioButton) view.findViewById(R.id.radio_cash);

        tv_distance=(TextView) view.findViewById(R.id.order_distance);
        tv_quote=(TextView) view.findViewById(R.id.order_quote);
        tv_time=(TextView) view.findViewById(R.id.order_time);


        onClick lis=new onClick();

        bt_cash.setOnClickListener(lis);
        bt_card.setOnClickListener(lis);


        tv_distance.setText(((main)getActivity()).distance);
        tv_time.setText(((main)getActivity()).time);



        return view;

    }

    @Override
    public void onStart(){
        super.onStart();






    }



    @Override
    public void onPause(){
        super.onPause();
        ((main)getActivity()).hopemoney = hope_money.getText().toString();
        ((main)getActivity()).paytype = pay;


    }

    class onClick implements View.OnClickListener{
        public void onClick(View v){
            switch (v.getId()){
                case R.id.radio_cash :
                    pay=0;
                    break;
                case R.id.radio_card:
                    pay=1;
                    break;
            }

        }
    }



}

