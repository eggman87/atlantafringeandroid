package com.atl.fringe.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.atl.fringe.R;
import com.atl.fringe.service.FringeService;
import com.octo.android.robospice.SpiceManager;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:28 AM
 */
public class BaseFragment extends RoboFragment{

    protected SpiceManager spiceManager = new SpiceManager(FringeService.class);

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
    }

}
