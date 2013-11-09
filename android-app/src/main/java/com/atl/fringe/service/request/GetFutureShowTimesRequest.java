package com.atl.fringe.service.request;

import android.content.Context;
import com.fringe.datacontract.ShowTime;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 3:07 PM
 */
public class GetFutureShowTimesRequest extends FringeRequest<GetFutureShowTimesRequest.GetFutureShowTimesResponse> {

    public GetFutureShowTimesRequest(Context context) {
        super(context, GetFutureShowTimesResponse.class);
    }

    @Override
    public GetFutureShowTimesResponse loadDataFromNetwork() throws Exception {
        List<ShowTime> showTimes = fringeService.getAllFutureShowtimes();
        GetFutureShowTimesResponse response = new GetFutureShowTimesResponse();
        response.futureShowTimes = showTimes;
        return response;
    }

    public static class GetFutureShowTimesResponse {
        public List<ShowTime> futureShowTimes;
    }
}
