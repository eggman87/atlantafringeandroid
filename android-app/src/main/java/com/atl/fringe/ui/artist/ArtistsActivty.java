package com.atl.fringe.ui.artist;

import android.os.Bundle;
import com.atl.fringe.R;
import com.atl.fringe.ui.BaseActivity;
import com.atl.fringe.ui.artist.fragment.ArtistViewPagerFragment;
import com.atl.fringe.ui.navigation.NavigationTransaction;
import com.atl.fringe.ui.schedule.fragment.ScheduleFragmentFull;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 4:53 PM
 */
public class ArtistsActivty extends BaseActivity {

    private static final String FRAG_ARTIST_ALL = "frag:artists:all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!mIsFirstFragmentAttached) {
            NavigationTransaction transaction = new NavigationTransaction(R.id.act_base_content_frame, FRAG_ARTIST_ALL, ArtistViewPagerFragment.class);
            navigateToFragmentInternal(transaction);
        }
    }
}
