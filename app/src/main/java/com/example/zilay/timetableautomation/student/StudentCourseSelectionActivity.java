package com.example.zilay.timetableautomation.student;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
    private String list[] = {"item 1","item 2","item 2","item 2","item 2","item 2","item 2","item 2"};
    private String fruits[] = {"apple","banana","mango"};
    boolean[] checkitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_selection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        final ListView listView = (ListView) findViewById(R.id.lvcs);
        listView.setAdapter(adapter);
        checkitem = new boolean[list.length];
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item  =listView.getItemAtPosition(position).toString();
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(StudentCourseSelectionActivity.this);
                mbuilder.setTitle("Select Your Section");
                        mbuilder.setSingleChoiceItems(fruits, 0, null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String s1 = ""+whichButton;
                                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                                Toast.makeText(StudentCourseSelectionActivity.this,fruits[selectedPosition],Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
                //Toast.makeText(StudentCourseSelectionActivity.this,item, Toast.LENGTH_SHORT).show();

            }
        });
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
