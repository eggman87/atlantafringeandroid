package com.atl.fringe.ui.schedule.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.atl.fringe.R;
import com.atl.fringe.service.request.GetFutureShowTimesRequest;
import com.atl.fringe.ui.BaseActivity;
import com.atl.fringe.ui.BaseFragment;
import com.atl.fringe.ui.navigation.NavigationTransaction;
import com.atl.fringe.ui.schedule.ScheduleActivity;
import com.atl.fringe.ui.schedule.adapter.ShowTimeListAdapter;
import com.atl.fringe.ui.schedule.adapter.ViewPagerAdapter;
import com.fringe.datacontract.Photo;
import com.fringe.datacontract.ShowTime;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArtistInfoFragment extends BaseFragment {
    @InjectView(R.id.artist_picture_pager)ViewPager mPager;
    PageIndicator mIndicator;
    ViewPagerAdapter mAdapter;

    @InjectView(R.id.other_shows_listview)ListView listShowTimes;
    @InjectView(R.id.frag_artist_tv_other)TextView txtOtherShows;
    @InjectView(R.id.frag_artist_tv_show_title)TextView txtShowTitle;
    @InjectView(R.id.frag_artist_tv_artist_title)TextView txtArtistTitle;
    @InjectView(R.id.frag_artist_tv_artist_bio)TextView txtArtistBio;
    @InjectView(R.id.frag_artist_tv_show_info)TextView txtShowInfo;

    private ShowTime targetShowTime;

    public static final String ARGS_SHOW_TIME = "show_time_arg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag_artist_info, container, false);
        mIndicator = (PageIndicator) view.findViewById(R.id.artist_picture_indicator);
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
                    List<ShowTime> showsAtThisVenue = new ArrayList<ShowTime>();
                    for (ShowTime time : getFutureShowTimesResponse.futureShowTimes) {
                        if (time.show.artist.stageName.equals(targetShowTime.show.artist.stageName)) {
                            showsAtThisVenue.add(time);
                        }
                    }

                    listShowTimes.setAdapter(new ShowTimeListAdapter(showsAtThisVenue, new ShowTimeListAdapter.ItemClickListener() {
                        @Override
                        public void onItemAddClick(ShowTime showTime) {
                            Bundle args = new Bundle();
                            args.putParcelable(ArtistInfoFragment.ARGS_SHOW_TIME, showTime);

                            NavigationTransaction transaction = new NavigationTransaction.Builder(R.id.act_base_content_frame, "aritst:info", ArtistInfoFragment.class)
                                    .setIsNavigationChild(true)
                                    .setArgs(args)
                                    .setAddToBackStack(true)
                                    .build();
                            ((BaseActivity)getActivity()).navigateToFragment(transaction);
                        }
                    }));
                }
            };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {

            targetShowTime = args.getParcelable(ARGS_SHOW_TIME);

            List<String> imageUrls = new ArrayList<String>();
            for (Photo photo : targetShowTime.show.artist.photos) {
                imageUrls.add(photo.url);
            }

            mAdapter = new ViewPagerAdapter((getActivity()).getSupportFragmentManager(), imageUrls);
            mPager.setAdapter(mAdapter);
            mIndicator.setViewPager(mPager);

            spiceManager.execute(new GetFutureShowTimesRequest(getActivity()), showTimesListener);

            setupView();
        }

    }

    private void setupView() {
        getActivity().getActionBar().setTitle(targetShowTime.show.artist.stageName + " @ " + targetShowTime.venue.name) ;

        txtOtherShows.setText("More from " + targetShowTime.show.artist.stageName);
        txtShowTitle.setText(targetShowTime.show.title);
        txtShowInfo.setText(targetShowTime.show.description);

        txtArtistTitle.setText(targetShowTime.show.artist.stageName);
        txtArtistBio.setText(targetShowTime.show.artist.description);
    }
}
