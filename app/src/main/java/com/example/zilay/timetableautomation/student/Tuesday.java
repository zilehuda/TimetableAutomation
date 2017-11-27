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
import android.widget.Toast;

import com.example.zilay.timetableautomation.ApiClient;
import com.example.zilay.timetableautomation.ApiInterface;
import com.example.zilay.timetableautomation.R;
import com.example.zilay.timetableautomation.adaptars.RecycleViewAdaptar;
import com.example.zilay.timetableautomation.adaptars.RetrofitAdaptar;
import com.example.zilay.timetableautomation.models.Courses;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Tuesday extends Fragment {
    private RecyclerView recyclerView;
    private RetrofitAdaptar retrofitAdaptar;
    private List<Courses> coursesList;
    private ApiInterface apiInterface;

    public Tuesday() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tuesday, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rvTimeTableTues);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        JSONObject timetableObject = new JSONObject();


        JsonObject obj = new JsonObject();
        obj.addProperty("code","CL101");


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Courses>> call = apiInterface.getCoursesPost(obj);

        call.enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(Call<List<Courses>> call, Response<List<Courses>> response) {
                coursesList = response.body();
                retrofitAdaptar = new RetrofitAdaptar(coursesList);
                recyclerView.setAdapter(retrofitAdaptar);

            }

            @Override
            public void onFailure(Call<List<Courses>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
