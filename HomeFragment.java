package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        CardView c1 = rootView.findViewById(R.id.card1);
        CardView c2 = rootView.findViewById(R.id.card2);
        CardView c3 = rootView.findViewById(R.id.card3);
        CardView c4 = rootView.findViewById(R.id.card4);
        CardView c5 = rootView.findViewById(R.id.card5);
        CardView c6 = rootView.findViewById(R.id.card6);

        c1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), CalmActivity.class);
                startActivity(i);
            }
        });

        c2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), SadActivity.class);
                startActivity(i);
            }
        });

        c3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), PanickedActivity.class);
                startActivity(i);
            }
        });

        c4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), HappyActivity.class);
                startActivity(i);
            }
        });

        c5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), TiredActivity.class);
                startActivity(i);
            }
        });

        c6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(getActivity(), AnxiousActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}
