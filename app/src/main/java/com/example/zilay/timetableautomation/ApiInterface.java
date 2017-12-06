package com.example.zilay.timetableautomation;



import com.example.zilay.timetableautomation.models.Courses;
import com.example.zilay.timetableautomation.models.Timetable;
import com.example.zilay.timetableautomation.models.tt;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zilay on 11/8/17.
 */

public interface ApiInterface {

    @GET("courses")
    Call <List<Courses>> getCourses();

    @POST("batch")
    Call <List<Courses>> getBatchCourses(@Body JsonObject jsonObject);

    @POST("courses")
    Call <List<Courses>> getCoursesPost(@Body JsonObject jsonObject);

    @POST("time")
    Call <List<Timetable>> getTimetable(@Body JsonArray jsonObject);

}
