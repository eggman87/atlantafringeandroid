package com.atl.fringe.ui.schedule;

import android.os.Bundle;
import com.atl.fringe.R;
import com.atl.fringe.ui.BaseActivity;
import com.atl.fringe.ui.navigation.NavigationTransaction;
import com.atl.fringe.ui.schedule.fragment.ScheduleFragmentFull;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:27 AM
 */
public class ScheduleActivity extends BaseActivity {

    private static final String FRAG_SCHED_FULL = "fragment:schedule:full";
    private static final String FRAG_SCHED_MY = "fragment:schedule:my";
    private static final String FRAG_SCHED_ARTISTINFO = "fragment:schedule:artistinfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //   NavigationTransaction transaction = new NavigationTransaction(R.id.act_base_content_frame, FRAG_SCHED_FULL, ScheduleFragmentFull.class);
        NavigationTransaction transaction = new NavigationTransaction(R.id.act_base_content_frame, FRAG_SCHED_ARTISTINFO, ArtistInfoFragment.class);

        navigateToFragmentInternal(transaction);
    }
}
