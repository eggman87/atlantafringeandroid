package com.fringe.datacontract;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 12:23 PM
 */
public class Venue implements Parcelable {

    public String name;
    public String longDescription;
    public Address address;
    public List<Photo> photos;
    public String phone;


    public Venue(){

    }

    protected Venue(Parcel in) {
        name = in.readString();
        longDescription = in.readString();
        address = (Address)in.readValue(null);
        photos = new ArrayList<Photo>();
        in.readList(photos, null);
        phone = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(longDescription);
        dest.writeValue(address);
        dest.writeList(photos);
        dest.writeString(phone);
    }

    public static final Parcelable.Creator<Venue> CREATOR = new Parcelable.Creator<Venue>() {
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
}