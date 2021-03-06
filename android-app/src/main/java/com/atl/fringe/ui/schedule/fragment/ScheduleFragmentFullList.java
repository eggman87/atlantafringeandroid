package com.atl.fringe.ui.schedule.fragment;

import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.atl.fringe.R;
import com.atl.fringe.service.request.GetFutureShowTimesRequest;
import com.atl.fringe.ui.BaseActivity;
import com.atl.fringe.ui.BaseFragment;
import com.atl.fringe.ui.navigation.NavigationTransaction;
import com.atl.fringe.ui.schedule.ScheduleActivity;
import com.atl.fringe.ui.schedule.adapter.ShowTimeListAdapter;
import com.fringe.datacontract.ShowTime;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import roboguice.inject.InjectView;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 1:24 PM
 */
public class ScheduleFragmentFullList extends BaseFragment {

    @InjectView(R.id.frag_sched_full_lv_showtimes)ListView listShowTimes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_schedule_full_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spiceManager.execute(new GetFutureShowTimesRequest(getActivity()), showTimesListener);

    }

    private RequestListener<GetFutureShowTimesRequest.GetFutureShowTimesResponse> showTimesListener = new
            RequestListener<GetFutureShowTimesRequest.GetFutureShowTimesResponse>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(getActivity(), "Unable to load showtimes... :(", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestSuccess(GetFutureShowTimesRequest.GetFutureShowTimesResponse getFutureShowTimesResponse) {
                listShowTimes.setAdapter(new ShowTimeListAdapter(getFutureShowTimesResponse.futureShowTimes, new ShowTimeListAdapter.ItemClickListener() {
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
}
