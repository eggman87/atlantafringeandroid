package com.fringe.datacontract;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 12:23 PM
 */
public class Address implements Parcelable{
    public String name;
    public String addressOne;
    public String addressTwo;
    public String city;
    public String state;
    public String zip;

    public double latitude;
    public double longitude;


    public Address() {

    }

    protected Address(Parcel in) {
        name = in.readString();
        addressOne = in.readString();
        addressTwo = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(addressOne);
        dest.writeString(addressTwo);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}