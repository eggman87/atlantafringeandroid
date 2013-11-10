package com.fringe.datacontract;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:44 AM
 */
public class Artist extends Person implements Parcelable {

    public int id;
    public String stageName;
    public String description;
    public String productionCompanyName;
    public List<Photo> photos;

    public Artist() {

    }

    protected Artist(Parcel in) {
        id = in.readInt();
        stageName = in.readString();
        description = in.readString();
        productionCompanyName = in.readString();
        photos = new ArrayList<Photo>();
        in.readList(photos, null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(stageName);
        dest.writeString(description);
        dest.writeString(productionCompanyName);
        dest.writeList(photos);
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}