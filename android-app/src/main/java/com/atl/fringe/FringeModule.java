package com.atl.fringe;

import com.fringe.service.IFringeService;
import com.fringe.service.MockFringeService;
import com.google.inject.AbstractModule;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:41 AM
 */
public class FringeModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(IFringeService.class).to(MockFringeService.class).asEagerSingleton();
    }
}
