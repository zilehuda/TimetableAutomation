package com.example.zilay.timetableautomation.adaptars;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zilay.timetableautomation.R;
import com.example.zilay.timetableautomation.models.Courses;

import java.util.List;

/**
 * Created by zilay on 11/8/17.
 */

public class RetrofitAdaptar extends RecyclerView.Adapter <RetrofitAdaptar.RetrofitViewHolder> {

    private List<Courses> coursesList;

    public RetrofitAdaptar(List<Courses> coursesList)
    {
        this.coursesList = coursesList;
    }


    @Override
    public RetrofitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_days,parent,false);
        return new RetrofitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RetrofitViewHolder holder, int position) {

        holder.subject.setText(coursesList.get(position).getTitle());
        holder.time.setText(coursesList.get(position).getCode());

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public class RetrofitViewHolder extends RecyclerView.ViewHolder
    {
        public TextView subject;
        public TextView section;
        public TextView time;

        public RetrofitViewHolder(View itemView) {
            super(itemView);
        }
    }
}
