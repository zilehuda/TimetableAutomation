package com.example.zilay.timetableautomation.student;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zilay.timetableautomation.ApiClient;
import com.example.zilay.timetableautomation.ApiInterface;
import com.example.zilay.timetableautomation.R;
import com.example.zilay.timetableautomation.models.Courses;
import com.example.zilay.timetableautomation.models.Timetable;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRegisterActivity extends AppCompatActivity {

    TextInputLayout batch;
    JSONObject courses;
    ApiInterface apiInterface;
    private List<Courses> CoursesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        batch = (TextInputLayout) findViewById(R.id.tilabatch);
    }

    public void GetCourses(View view)  {

        String bstring = batch.getEditText().getText().toString();
        getBatchCoursesFromServer(bstring);
    }

    public void getBatchCoursesFromServer(String bstring)
    {
        final JsonObject jsonOjbect = new JsonObject();
        jsonOjbect.addProperty("batch", bstring);
        CoursesList = new ArrayList<>();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Courses>> call = apiInterface.getBatchCourses(jsonOjbect);
        call.enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(Call<List<Courses>> call, Response<List<Courses>> response) {
                CoursesList = response.body();
                List<String> section = CoursesList.get(0).getSection();
                ChangeActivity(CoursesList);
            }

            @Override
            public void onFailure(Call<List<Courses>> call, Throwable t) {

            }
        });
    }

    public void ChangeActivity(List<Courses> CoursesList)
    {
        Intent intent = new Intent(this,StudentCourseSelectionActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("CoursesList",(ArrayList<Courses>)CoursesList);
        intent.putExtra("BUNDLE",args);
        //intent.putExtra("data",  CoursesList);
        startActivity(intent);
    }


}
