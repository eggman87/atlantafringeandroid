package com.atl.fringe.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.atl.fringe.R;
import com.atl.fringe.manager.SessionManager;
import com.atl.fringe.service.FringeService;
import com.atl.fringe.ui.navigation.NavigationTransaction;
import com.atl.fringe.ui.navigation.SlidingMenuFragment;
import com.octo.android.robospice.SpiceManager;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 9:26 AM
 */
public class BaseActivity extends RoboActionBarActivity {

    protected SpiceManager spiceManager = new SpiceManager(FringeService.class);
    protected SessionManager sessionManager = SessionManager.getInstance(this);

    private DrawerLayout mDrawerLayout;
    private FrameLayout mContentFrame;
    private LinearLayout mDrawerMenu;
    private ActionBarDrawerToggle mDrawerToggle;

    //keeps track of the number of fragments added (which are considered a sub navigation - meaning the up button should be displayed...)
    private int mSubNavigationCount = 0;
    protected boolean mIsFirstFragmentAttached;

    public static final String STATE_FIRST_ATTACHED = "first_attached";
    public static final String STATE_NAV_COUNT = "sub_navigation_count";

    private static final String TAG = "Fringe.BaseActivty";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(STATE_FIRST_ATTACHED, mIsFirstFragmentAttached);
        outState.putInt(STATE_NAV_COUNT, mSubNavigationCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
            restoreFromState(savedInstanceState);

        loadView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //if we are going back and we are in children navigation stack, keep track we are losing a child.
        if (mSubNavigationCount > 0)  {
            mSubNavigationCount --;
            //if there are no more children in navigation stack, renable drawertoggle
            if (mSubNavigationCount == 0)
                setActionBarState(false);
        }
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        if (mDrawerToggle != null)
            mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.isDrawerIndicatorEnabled() && mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                navigateToRootFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void navigateToFragment(NavigationTransaction transaction) {
        navigateToFragmentInternal(transaction);
    }

    private void restoreFromState(Bundle savedInstanceState) {
        mIsFirstFragmentAttached = savedInstanceState.getBoolean(STATE_FIRST_ATTACHED, false);
        mSubNavigationCount = savedInstanceState.getInt(STATE_NAV_COUNT, 0);
    }

    /**
     * Will return navigation to the root fragment of the current activity (essentially allowing you to perform up
     * navigation).
     */
    public void navigateToRootFragment() {
        mSubNavigationCount = 0;
        setActionBarState(false);

        FragmentManager fm= getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0){
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    /**
     * Navigates to a fragment. Meant for internal use by a activity instance to navigate to a fragment.
     * @param navigationTransaction a transaction that represents a navigation to a fragment.
     */
    protected void navigateToFragmentInternal(NavigationTransaction navigationTransaction) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(navigationTransaction.tag);
        if (fragment == null) {
            try {
                fragment = (Fragment)navigationTransaction.fragmentType.newInstance();
                fragment.setArguments(navigationTransaction.args);
            } catch (Exception e) {
                Log.e(TAG, "unable to instantiate fragment, are you sure your type is of fragment??? Does it have right access modifier???", e);
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (mIsFirstFragmentAttached) {
                if (navigationTransaction.addToBackStack)
                    transaction.addToBackStack(navigationTransaction.tag);
                //todo: fix animation logic
                if (navigationTransaction.animateToLeft)
                    transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right, R.anim.in_from_right, R.anim.out_to_left);
                else
                    transaction.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);

                transaction.replace(navigationTransaction.containerId, fragment, navigationTransaction.tag);
            } else {
                mIsFirstFragmentAttached = true;
                transaction.add(navigationTransaction.containerId, fragment, navigationTransaction.tag);
            }
            transaction.commit();

        } else if (!fragment.isVisible()){

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (navigationTransaction.addToBackStack)
                transaction.addToBackStack(navigationTransaction.tag);
            transaction.replace(navigationTransaction.containerId, fragment, navigationTransaction.tag);
            transaction.commit();
        }

        if (navigationTransaction.isNavigationChild) {
            mSubNavigationCount ++;
            if (mSubNavigationCount == 1)
                setActionBarState(true);
        }

    }

    /**
     * Loads the navigation menu. The actual content of the activity will be what is passed in by getContentViewId from
     * the child activity class.
     */
    private void setupMenu(){

        View baseView = View.inflate(this, R.layout.act_base, null);
        mDrawerLayout = (DrawerLayout) baseView.findViewById(R.id.act_base_drawer_layout);
        mContentFrame = (FrameLayout) baseView.findViewById(R.id.act_base_content_frame);
        mDrawerMenu = (LinearLayout) baseView.findViewById(R.id.act_base_menu_layout);

        setContentView(baseView);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_navigation_drawer, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SlidingMenuFragment menuFragment = new SlidingMenuFragment();
        transaction.add(R.id.act_base_menu_layout, menuFragment, "fragment:menu");
        transaction.commit();
    }

    private void loadView(){
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setupMenu();

        if (mSubNavigationCount > 0)
            setActionBarState(true);
    }

    private void setActionBarState(boolean isInUpMode) {
        mDrawerToggle.setDrawerIndicatorEnabled(!isInUpMode);
    }



}
