package com.fringe.datacontract;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:44 AM
 */
public class Artist extends Person {

    public int id;
    public String stageName;
    public String description;
    public String productionCompanyName;
    public Show show;
    public List<Photo> photos;

}
