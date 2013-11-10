package com.fringe.datacontract;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 1:08 PM
 */
public class ShowTime implements Parcelable {
    public Calendar startTime;
    public Calendar endTime;
    public Show show;
    public Venue venue;

    public ShowTime() {

    }

    protected ShowTime(Parcel in) {
        startTime = (Calendar)in.readSerializable();
        endTime = (Calendar)in.readSerializable();
        show = (Show)in.readValue(null);
        venue = (Venue)in.readValue(null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(startTime);
        dest.writeSerializable(endTime);
        dest.writeValue(show);
        dest.writeValue(venue);
    }

    public static final Parcelable.Creator<ShowTime> CREATOR = new Parcelable.Creator<ShowTime>() {
        public ShowTime createFromParcel(Parcel in) {
            return new ShowTime(in);
        }

        public ShowTime[] newArray(int size) {
            return new ShowTime[size];
        }
    };
}