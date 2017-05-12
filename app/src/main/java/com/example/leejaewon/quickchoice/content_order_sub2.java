package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by LeeJaeWon on 2017-05-01.
 */

public class content_order_sub2 extends Fragment {

    String hope;
    int pay;

    EditText hope_money;
    RadioButton bt_cash;
    RadioButton bt_card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         View view =inflater.inflate(R.layout.content_order_sub2,container,false);

        hope_money=(EditText) view.findViewById(R.id.order_hopemoney);

        bt_card=(RadioButton) view.findViewById(R.id.radio_card);
        bt_cash=(RadioButton) view.findViewById(R.id.radio_cash);

        onClick lis=new onClick();

        bt_cash.setOnClickListener(lis);
        bt_card.setOnClickListener(lis);

        return view;
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
