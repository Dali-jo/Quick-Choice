package com.example.leejaewon.quickchoice;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by LeeJaeWon on 2017-04-30.
 */

public class content_setting extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view=inflater.inflate(R.layout.content_setting,container,false);

        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView) view.findViewById(R.id.textView08);
        TextView textView2 = (TextView) view.findViewById(R.id.textView28);
        TextView textView3 = (TextView) view.findViewById(R.id.textView8);

        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface1);
        return view;
    }
}
