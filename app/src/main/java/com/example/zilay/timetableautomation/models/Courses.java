package com.example.zilay.timetableautomation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zilay on 11/8/17.
 */

public class Courses implements Serializable {

    @SerializedName("code")
    private String Code;

    @SerializedName("title")
    private String Title;

    @SerializedName("short")
    private String Short;

    @SerializedName("batch")
    private String batch;
    
    @SerializedName("section")
    private List<String>section;



    public void setCode(String code) {
        Code = code;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setShort(String aShort) {
        Short = aShort;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setSection(List<String> section) {
        this.section = section;
    }

    public String getCode() {
        return Code;
    }

    public String getTitle() {
        return Title;
    }

    public String getShort() {
        return Short;
    }

    public String getBatch() {
        return batch;
    }

    public List<String> getSection() {
        return section;
    }
}
