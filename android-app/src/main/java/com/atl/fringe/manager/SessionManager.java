package com.atl.fringe.manager;

import android.content.Context;
import com.fringe.datacontract.ShowTime;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 6:47 PM
 */
public class SessionManager {

    public List<ShowTime> showTimes;

    private boolean needsToSave;
    private static SessionManager sessionManager;

    public static SessionManager getInstance(Context context) {
        if (sessionManager == null) {
            sessionManager = new SessionManager(context);
        }

        return sessionManager;
    }

    private SessionManager(Context context) {

    }

    public void setFutureShowTimes(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
        needsToSave = true;
    }

    public List<ShowTime> getShowTimes() {
        return showTimes;
    }

}
