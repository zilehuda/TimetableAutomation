package com.example.zilay.timetableautomation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zilay on 11/4/17.
 */

public class Timetable {

    public Timetable() {

    }

    @SerializedName("short")
    private String course_short;

    @SerializedName("day")
    private String day;

    @SerializedName("slot")
    private String slot;

    @SerializedName("starttime")
    private String starttime;

    @SerializedName("endtime")
    private String endtime;

    @SerializedName("teacher_name")
    private String teacher_name;

    @SerializedName("subject")
    private String subject;

    @SerializedName("section")
    private String section;

    private Timetable timetable;

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Timetable(String subject, String section, String time)
    {
        this.subject = subject;
        this.section = section;
    }

    public void setSubject(String subject){this.subject=subject;}
    public void setSection(String section){this.section=section;}
    public void setStarttime(String starttime){this.starttime = starttime;}
    public void setEndtime(String endtime){this.endtime = endtime;}
    public void setSlot(String slot){this.slot = slot;}

    //getter
    public String getSubject(){return this.subject;}
    public String getCourse_short(){return this.course_short;}
    public String getSection(){return this.section;}
    public String getStarttime(){return this.starttime;}

    public String getday() {
        return day;
    }

    public String getSlot() {
        return slot;
    }

    public String getTeacherName() {
        return teacher_name;
    }

    public String getendtime() {
        return endtime;
    }
}
