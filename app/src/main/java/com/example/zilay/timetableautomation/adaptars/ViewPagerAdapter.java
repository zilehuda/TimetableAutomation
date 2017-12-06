package com.example.zilay.timetableautomation.adaptars;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.zilay.timetableautomation.student.Day;

/**
 * Created by zilay on 11/3/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Day monday = new Day();
                monday.setDay("Monday");
                return monday;

            case 1:
                Day tuesday = new Day();
                tuesday.setDay("Tuesday");
                return tuesday;
            case 2:
                Day wed = new Day();
                wed.setDay("Wednesday");
                return wed;
            case 3:
                Day thurs = new Day();
                thurs.setDay("Thursday");
                return thurs;
            case 4:
                Day fri = new Day();
                fri.setDay("Friday");
                return fri;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 5;
    }
}
