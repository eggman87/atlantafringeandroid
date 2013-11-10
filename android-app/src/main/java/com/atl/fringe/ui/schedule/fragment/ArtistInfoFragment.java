package com.atl.fringe.ui.schedule.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.ListView;
import android.widget.Toast;
import com.atl.fringe.R;
import com.atl.fringe.service.request.GetFutureShowTimesRequest;
import com.atl.fringe.ui.BaseFragment;
import com.atl.fringe.ui.schedule.ScheduleActivity;
import com.atl.fringe.ui.schedule.adapter.ShowTimeListAdapter;
import com.atl.fringe.ui.schedule.adapter.ViewPagerAdapter;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArtistInfoFragment extends BaseFragment {
    ViewPager mPager;
    PageIndicator mIndicator;
    ViewPagerAdapter mAdapter;

    @InjectView(R.id.other_shows_listview)ListView listShowTimes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag_artist_info, container, false);
        mPager = (ViewPager) view.findViewById(R.id.artist_picture_pager);
        mIndicator = (CirclePageIndicator) view.findViewById(R.id.artist_picture_indicator);
        mAdapter = new ViewPagerAdapter(((ScheduleActivity)getActivity()).getSupportFragmentManager());
        mPager.setAdapter(mAdapter);

        mIndicator.setViewPager(mPager);
        ((CirclePageIndicator) mIndicator).setSnap(true);
        spiceManager.execute(new GetFutureShowTimesRequest(getActivity()), showTimesListener);
        return view;
    }
    private RequestListener<GetFutureShowTimesRequest.GetFutureShowTimesResponse> showTimesListener = new
            RequestListener<GetFutureShowTimesRequest.GetFutureShowTimesResponse>() {
                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    Toast.makeText(getActivity(), "Unable to load showtimes... :(", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onRequestSuccess(GetFutureShowTimesRequest.GetFutureShowTimesResponse getFutureShowTimesResponse) {
                    listShowTimes.setAdapter(new ShowTimeListAdapter(getFutureShowTimesResponse.futureShowTimes));
                }
            };
}
