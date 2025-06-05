package com.example.miniproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.ArrayList;

public class YogaFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<RCModel> modelArrayList;
    RCAdapter rcAdapter;

    GridLayout grid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yoga, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        rcAdapter = new RCAdapter(getContext(), modelArrayList);
        recyclerView.setAdapter(rcAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        rcAdapter.notifyDataSetChanged();

        return view;
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        modelArrayList = new ArrayList<>();
        modelArrayList.add(new RCModel("BEGINNER LEVEL", R.drawable.beginner));
        modelArrayList.add(new RCModel("INTERMEDIATE LEVEL", R.drawable.intermediate));
        modelArrayList.add(new RCModel("ADVANCED LEVEL", R.drawable.advanced));
        modelArrayList.add(new RCModel("SURYANAMASKAR STEPS", R.drawable.surya));
        modelArrayList.add(new RCModel("REFERENCE BLOG", R.drawable.blog));

    }
}
