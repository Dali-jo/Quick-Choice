package com.example.leejaewon.quickchoice;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by LeeJaeWon on 2017-05-01.
 */

public class content_order_sub3 extends Fragment {

    String pickup;
    String memo;
    int fast;

    static final int AA=0;
    Button btnSelectDate,btnSelectTime;
    EditText dt_date;
    EditText dt_time;
    EditText text_memo;

    CheckBox check_fast;


    public int year,month,day,hour,minute;
    public int myear,mmonth,mday,mhour,mminute;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.content_order_sub3,container,false);

        text_memo=(EditText) view.findViewById(R.id.order_memo);

        check_fast=(CheckBox) view.findViewById(R.id.order_fast);

        dt_date=(EditText) view.findViewById(R.id.order_pickupdate);
        dt_time=(EditText) view.findViewById(R.id.order_pickuptime);

        btnSelectDate=(Button) view.findViewById(R.id.select_date) ;
        btnSelectTime =(Button) view.findViewById(R.id.select_time) ;
        btnSelectDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onCreateDialog(0);
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onCreateDialog(1);
            }
        });

        return view;
    }

    @Override
    public void onPause(){
        super.onPause();

        if(check_fast.isChecked()){
            fast=1;
        }else{
            fast=0;
        }
        ((main)getActivity()).fast=this.fast;
        ((main)getActivity()).pickup = dt_date.getText().toString()+dt_time.getText().toString();
        ((main)getActivity()).memo = text_memo.getText().toString();
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int yearSelected, int monthOfYeaar, int dayOfMonth){
            year=yearSelected;
            month=monthOfYeaar;
            day=dayOfMonth;
            Toast.makeText(getActivity(),"날짜 : "+year+month+day,Toast.LENGTH_LONG).show();
            String date =  String.valueOf(year)+String.valueOf(month)+String.valueOf(day);

            dt_date.setText(date);
        }

    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view, int hourOfDay, int min){
            hour=hourOfDay;
            minute=min;
            Toast.makeText(getActivity(),"시간 : "+hour+minute,Toast.LENGTH_LONG).show();
            String time = String.valueOf(hour)+String.valueOf(minute);
            dt_time.setText(time);
        }

    };


    protected Dialog onCreateDialog (int id){
        Calendar c = Calendar.getInstance();
        switch (id){
            case 0:

                myear = c.get(Calendar.YEAR);
                mmonth = c.get(Calendar.MONTH);
                mday = c.get(Calendar.DAY_OF_MONTH);
                 new DatePickerDialog(getActivity(),mDateSetListener,myear,month,mday).show();

                break;

            case 1:
                mhour = c.get(Calendar.HOUR_OF_DAY);
                mminute = c.get(Calendar.MINUTE);
                 new TimePickerDialog(getActivity(),mTimeSetListener,mhour,mminute,false).show();
                break;

        }
        return null;
    }

}
