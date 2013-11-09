package com.atl.fringe.ui.start;

import android.os.Bundle;
import com.atl.fringe.service.request.GetArtistsRequest;
import com.atl.fringe.ui.BaseActivity;
import com.fringe.service.IFringeService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.inject.Inject;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:27 AM
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sample to show making a request...
        GetArtistsRequest request = new GetArtistsRequest(this);

        spiceManager.execute(request, artistsResponse);

    }

    private RequestListener<GetArtistsRequest.ArtistResponse> artistsResponse = new RequestListener<GetArtistsRequest.ArtistResponse>() {
        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(GetArtistsRequest.ArtistResponse artistResponse) {

        }
    };
}
