package com.example.zilay.timetableautomation;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zilay on 11/8/17.
 */

public class ApiClient {


    private static String BASE_URL = "http://192.168.1.112:80/timetable/public/api/student/";

    public static Retrofit retrofit = null;


    public static Retrofit getApiClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
