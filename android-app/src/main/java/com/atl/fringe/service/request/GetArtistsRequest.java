package com.atl.fringe.service.request;

import android.content.Context;
import com.fringe.datacontract.Artist;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 10:11 AM
 */
public class GetArtistsRequest extends FringeRequest<GetArtistsRequest.ArtistResponse> {

    public GetArtistsRequest(Context context) {
        super(context, ArtistResponse.class);
    }

    @Override
    public ArtistResponse loadDataFromNetwork() throws Exception {
        ArtistResponse response = new ArtistResponse();
        response.artists = fringeService.getAllArtists();
        return response;
    }

    public static class ArtistResponse {
        public List<Artist> artists;
    }
}
