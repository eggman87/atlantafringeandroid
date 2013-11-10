package com.atl.fringe.ui.schedule.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.*;
import com.atl.fringe.R;
import com.atl.fringe.ui.BaseFragment;
import com.atl.fringe.ui.schedule.adapter.ScheduleFullViewPagerAdapter;
import roboguice.inject.InjectView;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 5:14 PM
 */
public class ScheduleFragmentFull extends BaseFragment {


    @InjectView(R.id.frag_schedule_full_vp) protected ViewPager viewPager;

    private boolean adapterSet;
    private int currentPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_schedule_full, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        setHasOptionsMenu(true);

        if (!adapterSet){
            ScheduleFullViewPagerAdapter adapter = new ScheduleFullViewPagerAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i2) {

                }

                @Override
                public void onPageSelected(int i) {
                    if (currentPage != i) {
                        currentPage = i;
                        getActivity().invalidateOptionsMenu();
                    }

                    if (currentPage == 0) {
                        getActivity().getActionBar().setTitle("Upcoming Events");
                    } else {
                        getActivity().getActionBar().setTitle("Fringe Venues");
                    }


                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
            adapterSet = true;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if (currentPage == 1) {
            menu.add(0, 1, 1, "List View")
                    .setIcon(android.R.drawable.ic_media_previous)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        } else {
            menu.add(0, 2, 1, "Map View")
                    .setIcon(R.drawable.map_pin)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==1) {
            viewPager.setCurrentItem(0);
        } else if (item.getItemId() == 2) {
            viewPager.setCurrentItem(1);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getActionBar().setTitle("Upcoming Events");
    }
}
