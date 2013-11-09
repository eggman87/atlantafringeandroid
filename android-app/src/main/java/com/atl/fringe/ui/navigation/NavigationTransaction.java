package com.atl.fringe.ui.navigation;

import android.os.Bundle;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 10:34 AM
 */
public class NavigationTransaction {

    public Bundle args;
    public int containerId;
    public String tag;
    public boolean isNavigationChild;
    public boolean animateToLeft;
    public boolean addToBackStack = true;
    public Class<?> fragmentType;

    public static class Builder {

        private Bundle args;
        private int containerId;
        private String tag;
        private boolean isNavigationChild;
        private boolean animateToLeft;
        private boolean addToBackStack = true;
        private Class<?> fragmentType;

        public Builder(int containerId, String tag, Class<?> fragmentType) {
            this.containerId = containerId;
            this.tag = tag;
            this.fragmentType = fragmentType;
        }

        public Builder setArgs(Bundle args) {
            this.args = args;
            return this;
        }

        public Builder setIsNavigationChild(boolean isNavigationChild) {
            this.isNavigationChild = isNavigationChild;
            return this;
        }

        public Builder setAnimateToLeft(boolean animateToLeft) {
            this.animateToLeft = animateToLeft;
            return this;
        }

        public Builder setAddToBackStack(boolean addToBackStack) {
            this.addToBackStack = addToBackStack;
            return this;
        }

        public NavigationTransaction build() {
            return new NavigationTransaction(this);
        }
    }

    public NavigationTransaction(int containerId, String tag, Class<?> fragmentType) {
        this.containerId = containerId;
        this.tag = tag;
        this.fragmentType = fragmentType;
    }

    private NavigationTransaction(Builder b) {
        this.containerId = b.containerId;
        this.tag = b.tag;
        this.fragmentType = b.fragmentType;

        this.args = b.args;
        this.isNavigationChild = b.isNavigationChild;
        this.animateToLeft = b.animateToLeft;
        this.addToBackStack = b.addToBackStack;
    }

}