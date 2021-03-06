package com.fringe.service;

import com.fringe.datacontract.Artist;
import com.fringe.datacontract.ShowTime;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:43 AM
 */
public interface IFringeService {

    List<Artist> getAllArtists();

    List<ShowTime> getAllFutureShowtimes();
}
