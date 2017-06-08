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

public class content_main extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.content_main,container,false);
        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/godic.ttf");
        TextView textView1 = (TextView) view.findViewById(R.id.button);
        TextView textView2 = (TextView) view.findViewById(R.id.button2);

        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        return view;
    }

}
