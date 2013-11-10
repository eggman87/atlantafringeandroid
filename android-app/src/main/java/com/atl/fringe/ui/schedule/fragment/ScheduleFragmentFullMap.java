package com.atl.fringe.ui.schedule.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.*;
import android.view.animation.Animation;
import android.widget.*;
import com.atl.fringe.R;
import com.atl.fringe.service.request.GetFutureShowTimesRequest;
import com.atl.fringe.ui.BaseActivity;
import com.atl.fringe.ui.BaseFragment;
import com.atl.fringe.ui.animation.DropDownAnimation;
import com.atl.fringe.ui.animation.DropDownMarginTopAnimation;
import com.atl.fringe.ui.component.FixedSlidingDrawer;
import com.atl.fringe.ui.navigation.NavigationTransaction;
import com.atl.fringe.ui.schedule.adapter.ShowTimeListAdapter;
import com.fringe.datacontract.ShowTime;
import com.fringe.datacontract.Venue;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import roboguice.inject.InjectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 5:08 PM
 */
public class ScheduleFragmentFullMap extends BaseFragment {

    @InjectView(R.id.frag_schedule_details_panel)RelativeLayout detailsPanel;
    @InjectView(R.id.frag_sched_full_iv_close)ImageButton btnClose;
    @InjectView(R.id.frag_sched_st_tv_time)TextView txtTime;
    @InjectView(R.id.frag_sched_st_tv_title)TextView txtTitle;
    @InjectView(R.id.frag_sched_st_tv_sub_title)TextView txtSubTitle;
    @InjectView(R.id.frag_schedule_details_drawer)FixedSlidingDrawer contentDrawer;
    @InjectView(R.id.frag_sched_st_tv_show_showtimes)TextView txtMoreTimes;
    @InjectView(R.id.frag_sched_st_lv_show_showtimes)ListView listTimes;

    private int oldDrawerMargin;

    private HashMap<Marker, ShowTime> cachedMarkers;
    protected MapView mapView;

    private ShowTime targetTime;

    private GoogleMap map;
    private List<ShowTime> showTimes;
    private boolean detailsPanelOpen;

    private static final String TAG = "Fringe.ScheduleFragmentFullMap";

    public ScheduleFragmentFullMap() {
        setHasOptionsMenu(true);
    }


    @Override
    public void onResume() {
        super.onResume();

        mapView.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mapView != null)
            mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_schedule_full_map, container, false);

        mapView = (MapView)view.findViewById(R.id.frag_schedule_full_map);

        return  view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (display != null) {
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            ((RelativeLayout.LayoutParams)contentDrawer.getLayoutParams()).setMargins(0, (height /4) *2, 0, 0);
            contentDrawer.requestLayout();
        }

        mapView.onCreate(savedInstanceState);
        map = mapView.getMap();

        initMap();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void moveTheCameraToPosition(LatLng latLng, int zoom){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(zoom)
                .bearing(0)
                .tilt(30)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void initMap(){
        if (map != null){

            try {
                MapsInitializer.initialize(getActivity());
               // mMapsEnabled = true;
            } catch (GooglePlayServicesNotAvailableException e) {
                Log.d(TAG, "Map services are not available on this device.");
            }

            UiSettings settings = map.getUiSettings();
            settings.setAllGesturesEnabled(true);
            settings.setMyLocationButtonEnabled(true);
            settings.setZoomControlsEnabled(false);

            map.setMyLocationEnabled(true);
            moveTheCameraToPosition(new LatLng(33.763665, -84.350352), 12);

            spiceManager.execute(new GetFutureShowTimesRequest(getActivity()), showTimesListener);

            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (detailsPanelOpen) {
                        detailsPanelOpen = false;
                        contentDrawer.animateToggle();

//                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) contentDrawer.getLayoutParams();
//                        lp.setMargins(0, oldDrawerMargin, 0, 0);

                        if (timesShown) {
                            hideShowTimes();
                        }

                    }
                }
            });

            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if (!detailsPanelOpen) {
                        contentDrawer.animateToggle();
                    }

                    targetTime= cachedMarkers.get(marker);
                    Venue venue = targetTime.venue;

                    txtTime.setText(venue.name);
                    txtTitle.setText(venue.address.addressOne);
                    txtSubTitle.setText(venue.longDescription);

                    detailsPanelOpen = true;

                    return true;

                }
            });

            txtMoreTimes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!timesShown) {
                        showShowTimes();
                    } else {
                        hideShowTimes();
                    }
                }
            });
        }
    }

    private void showShowTimes(){
        DropDownMarginTopAnimation animation = new DropDownMarginTopAnimation(contentDrawer, 100, true);
        animation.setDuration(250);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) contentDrawer.getLayoutParams();
        oldDrawerMargin = lp.topMargin;

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txtMoreTimes.setText("↓ HIDE ↓");
                timesShown = true;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<ShowTime> venueTimes = new ArrayList<ShowTime>();
                for (ShowTime time : showTimes) {
                    if (time.venue.name.equals(targetTime.venue.name)){
                        venueTimes.add(time);
                    }
                }

                //prevents lag
                listTimes.setAdapter(new ShowTimeListAdapter(venueTimes, new ShowTimeListAdapter.ItemClickListener() {
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
        }, 350);

        contentDrawer.startAnimation(animation);
    }

    private void hideShowTimes(){
        DropDownMarginTopAnimation animation = new DropDownMarginTopAnimation(contentDrawer, oldDrawerMargin, true);
        animation.setDuration(250);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txtMoreTimes.setText("↑ SHOW TIMES ↑");
                listTimes.setAdapter(null);
                timesShown = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        contentDrawer.startAnimation(animation);
    }

    private boolean timesShown;

    private void addMarkersToTheMap(){
        cachedMarkers = new HashMap<Marker, ShowTime>();
        for (ShowTime showTime : showTimes) {
            Marker marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(showTime.venue.address.latitude, showTime.venue.address.longitude)));
            cachedMarkers.put(marker, showTime);

        }
    }

    private RequestListener<GetFutureShowTimesRequest.GetFutureShowTimesResponse> showTimesListener = new
            RequestListener<GetFutureShowTimesRequest.GetFutureShowTimesResponse>() {
                @Override
                public void onRequestFailure(SpiceException spiceException) {
                    Toast.makeText(getActivity(), "Unable to load showtimes... :(", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onRequestSuccess(GetFutureShowTimesRequest.GetFutureShowTimesResponse getFutureShowTimesResponse) {
                    showTimes = getFutureShowTimesResponse.futureShowTimes;
                    addMarkersToTheMap();
                }
            };
}
