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

public class content_service extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view=inflater.inflate(R.layout.content_service,container,false);
        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView) view.findViewById(R.id.button9);
        TextView textView2 = (TextView) view.findViewById(R.id.button10);
        TextView textView3 = (TextView) view.findViewById(R.id.button11);
        TextView textView4 = (TextView) view.findViewById(R.id.button14);

        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface1);
        textView4.setTypeface(typeface1);
        return view;
    }
}
