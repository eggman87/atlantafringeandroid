package com.fringe.datacontract;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 12:26 PM
 */
public class Photo implements Parcelable{
    public boolean isLocal;
    public String url;
    public String caption;

    public Photo() {

    }

    protected Photo(Parcel in) {
        isLocal = in.readByte() != 0x00;
        url = in.readString();
        caption = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isLocal ? 0x01 : 0x00));
        dest.writeString(url);
        dest.writeString(caption);
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}