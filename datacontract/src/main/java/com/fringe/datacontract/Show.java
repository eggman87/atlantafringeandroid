package com.fringe.datacontract;

import java.util.Calendar;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 11:52 AM
 */
public class Show {
    public int id;
    public Artist artist;
    public String title;
    public String synopsis;
    public boolean originalWork;
    public ProductionRight productionRight;
    public ProfessionalPermission permission;
    public Premier premier;
    public PerformanceType performanceType;
    public String otherPerformanceType;
    public ShowRating rating;
    public int minimumAge;
    public int numberOfPerformers;
    public int runningTimeInMinutes;
    public List<Photo> photos;
    public int maxOccupancy;
    public int currentOccupancy;
    public String ticketsUrl;
    public String facebookUrl;
    public String twitterTag;
}
