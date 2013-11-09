package com.fringe.datacontract;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 12:23 PM
 */
public class Venue {

    public String name;
    public String shortDescription;
    public String longDescription;
    public Address address;
    public List<Show> shows;
    public List<Photo> photos;

}
