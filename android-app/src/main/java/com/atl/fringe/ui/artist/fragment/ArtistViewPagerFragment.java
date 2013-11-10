package com.atl.fringe.ui.artist.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.atl.fringe.R;
import com.atl.fringe.ui.BaseFragment;
import com.atl.fringe.ui.artist.adapter.ArtistViewPagerAdapter;
import com.fringe.datacontract.Artist;
import com.fringe.service.IFringeService;
import com.viewpagerindicator.CirclePageIndicator;
import roboguice.inject.InjectView;

import javax.inject.Inject;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/10/13
 * Time: 12:43 PM
 */
public class ArtistViewPagerFragment extends BaseFragment {

    @Inject IFringeService fringeService;
    @InjectView(R.id.frag_artist_vp_artists)ViewPager viewPager;
    private CirclePageIndicator pageIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_artists, container, false);

        pageIndicator = (CirclePageIndicator) view.findViewById(R.id.frag_artist_cpi);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Artist> artists = fringeService.getAllArtists();
        viewPager.setAdapter(new ArtistViewPagerAdapter(getChildFragmentManager(), artists));
        pageIndicator.setViewPager(viewPager);

    }
}
