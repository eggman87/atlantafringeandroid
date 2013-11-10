package com.atl.fringe.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SlidingDrawer;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/10/13
 * Time: 12:29 AM
 */
public class FixedSlidingDrawer extends SlidingDrawer {

    public FixedSlidingDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedSlidingDrawer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec == MeasureSpec.UNSPECIFIED)
            widthMeasureSpec = MeasureSpec.AT_MOST;
        if (heightMeasureSpec == MeasureSpec.UNSPECIFIED)
            heightMeasureSpec = MeasureSpec.AT_MOST;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
