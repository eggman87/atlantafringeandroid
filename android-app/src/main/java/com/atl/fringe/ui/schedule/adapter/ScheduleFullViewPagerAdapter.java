package com.atl.fringe.ui.schedule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.atl.fringe.ui.schedule.fragment.ScheduleFragmentFullList;
import com.atl.fringe.ui.schedule.fragment.ScheduleFragmentFullMap;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 5:15 PM
 */
public class ScheduleFullViewPagerAdapter extends FragmentPagerAdapter {

    public ScheduleFullViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new ScheduleFragmentFullList();
            case 1:
                return new ScheduleFragmentFullMap();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
