package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    Button bt1;
    Button bt2;


    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.content_order_sub1,container,false);


        start=(EditText) view.findViewById(R.id.order_start);
        desty=(EditText) view.findViewById(R.id.order_desti);
        bt1=(Button) view.findViewById(R.id.search1);
        bt2=(Button) view.findViewById(R.id.search2);
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

        if(requestCode==0){
            if(resultCode==1){
                start.setText(data.getStringExtra("juso"));
                Toast.makeText(getActivity(), data.getStringExtra("juso")+"fragment", Toast.LENGTH_SHORT).show();

            }
        }
        if(requestCode==1){
            if(resultCode==1){
                desty.setText(data.getStringExtra("juso"));
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
