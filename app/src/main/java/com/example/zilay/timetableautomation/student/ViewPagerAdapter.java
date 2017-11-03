package com.example.zilay.timetableautomation.student;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
                Monday monday = new Monday();
                return monday;
            case 1:
                Tuesday tuesday = new Tuesday();
                return tuesday;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
