package com.example.zilay.timetableautomation.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.zilay.timetableautomation.ApiClient;
import com.example.zilay.timetableautomation.ApiInterface;
import com.example.zilay.timetableautomation.DatabaseHelper;
import com.example.zilay.timetableautomation.R;
import com.example.zilay.timetableautomation.models.Timetable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.gson.JsonObject;

public class
StudentCourseSelectionActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private List<Timetable> timetableslist;
    private DatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_selection);
    }



    public void GetTimetable(View view) {

        String f_code = "CS422";
        String section = "F";

        getFromServer(f_code,section);


    }

    public void getFromServer(String f_code,String section)
    {
        final JsonObject timetableOjbect = new JsonObject();
        timetableOjbect.addProperty("f_code", f_code);
        timetableOjbect.addProperty("section",section);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Timetable>> call = apiInterface.getTimetable(timetableOjbect);

        call.enqueue(new Callback<List<Timetable>>() {
            @Override
            public void onResponse(Call<List<Timetable>> call, Response<List<Timetable>> response) {

                timetableslist = response.body();
                SaveOnDb(timetableslist);
                Toast.makeText(getApplicationContext(),timetableslist.get(0).getCourse_short(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Timetable>> call, Throwable t) {

            }
        });
    }

    public void SaveOnDb(List<Timetable> timetableslist)
    {
        dbhelper = new DatabaseHelper(this);
        for (int i = 0 ; i < timetableslist.size() ; i++)
        {
            String course_short = timetableslist.get(i).getCourse_short();
            String day = timetableslist.get(i).getday();
            String section = timetableslist.get(i).getSection();
            String slot = timetableslist.get(i).getSlot();
            String teacher_name = timetableslist.get(i).getTeacherName();
            String starttime = timetableslist.get(i).getStarttime();
            String endtime = timetableslist.get(i).getendtime();
            dbhelper.insertData(course_short,day,slot,section,teacher_name,starttime,endtime);
        }

        Intent intent = new Intent(this,StudentMainActivity.class);
        startActivity(intent);
    }

}
