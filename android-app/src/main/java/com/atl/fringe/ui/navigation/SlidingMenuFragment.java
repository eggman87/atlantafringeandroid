package com.atl.fringe.ui.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.atl.fringe.R;
import com.atl.fringe.ui.BaseFragment;
import com.atl.fringe.ui.artist.ArtistsActivty;
import com.atl.fringe.ui.hive.HiveHotspotsActivity;
import com.atl.fringe.ui.navigation.adapter.NavigationMenuListAdapter;
import com.atl.fringe.ui.schedule.MyScheduleActivity;
import com.atl.fringe.ui.schedule.ScheduleActivity;
import com.atl.fringe.ui.venue.VenuesActivity;
import com.fringe.datacontract.Venue;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 10:39 AM
 */
public class SlidingMenuFragment extends BaseFragment {

    @InjectView(R.id.frag_sliding_menu_lv_menu)ListView listMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_sliding_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<NavigationMenuItem> navItems = new ArrayList<NavigationMenuItem>(5);
        navItems.add(new NavigationMenuItem("My Sched", "",R.drawable.icon_my_schedule , MyScheduleActivity.class));
        navItems.add(new NavigationMenuItem("Full Sched", "", R.drawable.icon_full_schedule, ScheduleActivity.class));
        navItems.add(new NavigationMenuItem("Hive Hotspots", "", R.drawable.icon_hive_hotspots, HiveHotspotsActivity.class));
        navItems.add(new NavigationMenuItem("Artists", "", R.drawable.icon_artists, ArtistsActivty.class));
        navItems.add(new NavigationMenuItem("Venues", "", R.drawable.icon_venues, VenuesActivity.class));

        listMenu.setAdapter(new NavigationMenuListAdapter(getActivity(), navItems));
    }
}
