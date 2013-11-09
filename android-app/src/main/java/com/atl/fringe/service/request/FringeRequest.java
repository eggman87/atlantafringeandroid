package com.atl.fringe.service.request;

import android.content.Context;
import com.fringe.service.IFringeService;
import com.octo.android.robospice.request.SpiceRequest;
import roboguice.RoboGuice;

import javax.inject.Inject;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 10:11 AM
 */
public abstract class FringeRequest<TResponseType> extends SpiceRequest<TResponseType> {

    @Inject protected IFringeService fringeService;

    public FringeRequest(Context context, Class<TResponseType> clazz) {
        super(clazz);

        RoboGuice.getInjector(context).injectMembers(this);
    }
}
