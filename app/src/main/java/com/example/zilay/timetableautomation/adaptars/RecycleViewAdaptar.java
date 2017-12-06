package com.example.zilay.timetableautomation.adaptars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zilay.timetableautomation.R;
import com.example.zilay.timetableautomation.models.Timetable;

import java.util.List;

/**
 * Created by zilay on 11/4/17.
 */

public class RecycleViewAdaptar extends RecyclerView.Adapter <RecycleViewAdaptar.ViewHolder> {

    public RecycleViewAdaptar(List<Timetable> listTimetable, Context context) {
        ListTimetable = listTimetable;
        this.context = context;
    }

    private List<Timetable> ListTimetable;
    private Context context;



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_days,parent,false);
            return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Timetable timetable = ListTimetable.get(position);

        holder.subject.setText(timetable.getSubject());
        holder.section.setText(timetable.getSection());
        holder.starttime.setText(timetable.getStarttime());
        holder.endtime.setText(timetable.getendtime());
        holder.slot.setText(timetable.getSlot());


    }

    @Override
    public int getItemCount() {
        return ListTimetable.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView subject;
        public TextView section;
        public TextView starttime;
        public TextView endtime;
        public TextView slot;
        public ViewHolder(View itemView) {
            super(itemView);

            subject = (TextView) itemView.findViewById(R.id.tvsubject);
            section = (TextView) itemView.findViewById(R.id.tvsection);
            starttime = (TextView) itemView.findViewById(R.id.tvstarttime);
            endtime = (TextView) itemView.findViewById(R.id.tvendtime);
            slot = (TextView) itemView.findViewById(R.id.tvslot);
        }
    }


}
