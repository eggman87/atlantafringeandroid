package com.atl.fringe.ui.navigation;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 4:28 PM
 */
public class NavigationMenuItem {

    public String title;
    public String subTitle;
    public int icon;
    public Class<?> activityType;

    public NavigationMenuItem(String tite, String subTitle, int icon, Class<?> activityType) {
        this.title = tite;
        this.subTitle = subTitle;
        this.icon = icon;
        this.activityType = activityType;
    }
}
