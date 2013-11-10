package com.atl.fringe.ui.artist.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.atl.fringe.ui.artist.fragment.ArtistPageFragment;
import com.fringe.datacontract.Artist;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/10/13
 * Time: 12:46 PM
 */
public class ArtistViewPagerAdapter extends FragmentPagerAdapter {

    private List<Artist> artists;

    public ArtistViewPagerAdapter(FragmentManager fm, List<Artist> artists) {
        super(fm);
        this.artists = artists;
    }

    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public Fragment getItem(int i) {
        return ArtistPageFragment.newInstance(artists.get(i));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
