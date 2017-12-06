package com.example.zilay.timetableautomation.student;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.preference.DialogPreference;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
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
    Button button;
    String strbatch;
    private List<Courses> CoursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.clear();
//        editor.commit();

        if(!isConnected(StudentRegisterActivity.this)) buildDialog(StudentRegisterActivity.this).show();
        else {

            setContentView(R.layout.activity_main);
        }

        setContentView(R.layout.activity_student_register);
        batch = (TextInputLayout) findViewById(R.id.tvrollnumber);
        button = (Button) findViewById(R.id.picker);
    }

    public void GetCourses(View view)  {

        String bstring = strbatch;
        getBatchCoursesFromServer(bstring);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public void getBatchCoursesFromServer(String bstring)
    {
        final JsonObject jsonOjbect = new JsonObject();
        jsonOjbect.addProperty("batch", bstring);
        CoursesList = new ArrayList<>();
        final ProgressDialog dialog = ProgressDialog.show(this, "", "Loading...",
                true);
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 3000); // 3000 milliseconds delay
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

    public void getPicker(View view)
    {
        numberPickerDialog();
    }

    public void numberPickerDialog()
    {
        NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMinValue(2014);
        numberPicker.setMaxValue(2017);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // do something he
                strbatch = newVal+"";

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(numberPicker);
        builder.setTitle("Select Batch");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                button.setText(strbatch);
            }
        }).show();

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }


    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });

        return builder;
    }
    }