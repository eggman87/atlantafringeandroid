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
        navItems.add(new NavigationMenuItem("My Sched", "it's your plan, stan", android.R.drawable.ic_menu_agenda, MyScheduleActivity.class));
        navItems.add(new NavigationMenuItem("Full Sched", "other fringetastic shows", android.R.drawable.ic_menu_info_details, ScheduleActivity.class));
        navItems.add(new NavigationMenuItem("Hive Hotspots", "buttonholders rejoce", android.R.drawable.ic_menu_today, HiveHotspotsActivity.class));
        navItems.add(new NavigationMenuItem("Artists", "the talent", android.R.drawable.ic_menu_manage, ArtistsActivty.class));
        navItems.add(new NavigationMenuItem("Venues", "where da shows b at", android.R.drawable.ic_menu_directions, VenuesActivity.class));

        listMenu.setAdapter(new NavigationMenuListAdapter(getActivity(), navItems));
    }
}
