package com.example.zilay.timetableautomation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zilay.timetableautomation.models.Timetable;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by zilay on 11/10/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "timetable.db";
    public static final String TABLE_NAME = "timetable_table";
    public static final String ID = "ID";
    public static final String COURSE_TITLE = "TITLE";
    public static final String DAY = "DAY";
    public static final String SLOT = "SLOT";
    public static final String SECTION = "SECTION";
    public static final String TEACHER_NAME = "TEACHER_NAME";
    public static final String START_TIME = "STARTTIME";
    public static final String END_TIME = "ENDTIME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DAY TEXT,SLOT TEXT,SECTION TEXT,TEACHER_NAME TEXT,STARTTIME TEXT,ENDTIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String day, String slot,String section,String teacherName,String start_time,String end_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_TITLE , title);
        contentValues.put(DAY,day);
        contentValues.put(SLOT,slot);
        contentValues.put(SECTION,section);
        contentValues.put(TEACHER_NAME,teacherName);
        contentValues.put(START_TIME,start_time);
        contentValues.put(END_TIME,end_time);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(int id) {

        String countQuery = "SELECT  * FROM student_table where ID="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        int count=0;
        Cursor cursor = db.rawQuery(countQuery, null);

        cursor.moveToFirst();
        return cursor;

    }
    public List<Timetable> getAllContacts() {
        List<Timetable> TimeTableList = new ArrayList<Timetable>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Timetable timetable = new Timetable();

                timetable.setSubject(cursor.getString(cursor.getColumnIndex(COURSE_TITLE)));
                timetable.setSection(cursor.getString(cursor.getColumnIndex(SECTION)));
                timetable.setStarttime(cursor.getString(cursor.getColumnIndex(DAY)));
                // Adding contact to list
                TimeTableList.add(timetable);
            } while (cursor.moveToNext());
        }

        // return contact list
        return TimeTableList;
    }
    public List<Timetable> getDayTimetable(String day) {
        List<Timetable> TimeTableList = new ArrayList<Timetable>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME   ;

        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = db.query(TABLE_NAME, null, "DAY=?", new String[] {day}, null, null, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Timetable timetable = new Timetable();

                timetable.setSubject(cursor.getString(cursor.getColumnIndex(COURSE_TITLE)));
                timetable.setSection(cursor.getString(cursor.getColumnIndex(SECTION)));
                timetable.setStarttime(cursor.getString(cursor.getColumnIndex(START_TIME)));
                timetable.setEndtime(cursor.getString(cursor.getColumnIndex(END_TIME)));
                timetable.setSlot(cursor.getString(cursor.getColumnIndex(SLOT)));
                // Adding contact to list
                TimeTableList.add(timetable);
            } while (cursor.moveToNext());
        }

        // return contact list
        return TimeTableList;
    }
    public int getTotalRows() {

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        int count=0;
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor != null) {

            count = cursor.getCount();
            cursor.close();
        }

        return count;
    }
}