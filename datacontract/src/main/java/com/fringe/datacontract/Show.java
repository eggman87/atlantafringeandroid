package com.fringe.datacontract;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 11:52 AM
 */
public class Show implements Parcelable{
    public int id;
    public Artist artist;
    public String title;
    public boolean originalWork;
    public PerformanceType performanceType;
    public int minimumAge;
    public int numberOfPerformers;
    public int runningTimeInMinutes;
    public List<Photo> photos;
    public int maxOccupancy;
    public int currentOccupancy;
    public String ticketsUrl;
    public String facebookUrl;
    public String twitterTag;
    public String description;

    public Show() {

    }

    protected Show(Parcel in) {
        id = in.readInt();
        artist = (Artist)in.readValue(null);
        title = in.readString();
        originalWork = in.readByte() != 0x00;
        performanceType = PerformanceType.values()[in.readInt()];
        minimumAge = in.readInt();
        numberOfPerformers = in.readInt();
        runningTimeInMinutes = in.readInt();
        photos = new ArrayList<Photo>();
        in.readList(photos, null);
        maxOccupancy = in.readInt();
        currentOccupancy = in.readInt();
        ticketsUrl = in.readString();
        facebookUrl = in.readString();
        twitterTag = in.readString();
        description = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeValue(artist);
        dest.writeString(title);
        dest.writeByte((byte) (originalWork ? 0x01 : 0x00));
        dest.writeInt(performanceType.ordinal());
        dest.writeInt(minimumAge);
        dest.writeInt(numberOfPerformers);
        dest.writeInt(runningTimeInMinutes);
        dest.writeList(photos);
        dest.writeInt(maxOccupancy);
        dest.writeInt(currentOccupancy);
        dest.writeString(ticketsUrl);
        dest.writeString(facebookUrl);
        dest.writeString(twitterTag);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<Show> CREATOR = new Parcelable.Creator<Show>() {
        public Show createFromParcel(Parcel in) {
            return new Show(in);
        }

        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
}