package com.atl.fringe.ui.schedule.adapter;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.atl.fringe.R;
import com.atl.fringe.ui.schedule.PhotoHolderFragment;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> imageUrls;

    public ViewPagerAdapter(FragmentManager fm, List<String> imageurls){
        super(fm);
        this.imageUrls = imageurls;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoHolderFragment.newInstance(imageUrls.get(position));
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }
}
