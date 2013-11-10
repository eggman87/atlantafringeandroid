package com.atl.fringe.ui.schedule.adapter;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.atl.fringe.R;
import com.atl.fringe.ui.schedule.PhotoHolderFragment;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter implements
        IconPagerAdapter {
    int[] Images = new int[] {R.drawable.beatles1, R.drawable.beatles2, R.drawable.beatles3};
    int[] dots = new int[] {R.drawable.opencircle,R.drawable.opencircle,R.drawable.opencircle};

    int mCount = Images.length;


    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PhotoHolderFragment pf = new PhotoHolderFragment(Images[position]);
        return pf;
    }

    @Override
    public int getIconResId(int index) {
        return dots[index % dots.length];
    }

    @Override
    public int getCount() {
        return mCount;
    }


    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}
