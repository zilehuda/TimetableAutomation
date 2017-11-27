package com.example.zilay.timetableautomation.student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zilay.timetableautomation.DatabaseHelper;
import com.example.zilay.timetableautomation.R;
import com.example.zilay.timetableautomation.adaptars.RecycleViewAdaptar;
import com.example.zilay.timetableautomation.models.Courses;
import com.example.zilay.timetableautomation.models.Timetable;

import java.util.ArrayList;
import java.util.List;

public class Monday extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Timetable> timetableList;
    private List<Courses> coursesList;
    DatabaseHelper dbhelper;
    public Monday()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monday, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rvTimeTable);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dbhelper = new DatabaseHelper(getContext());
        timetableList = new ArrayList<>();
        timetableList = dbhelper.getAllContacts();




        adapter = new RecycleViewAdaptar(timetableList,getContext());
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

}
