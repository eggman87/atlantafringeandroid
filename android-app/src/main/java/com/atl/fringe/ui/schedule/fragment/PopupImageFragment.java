package com.atl.fringe.ui.schedule.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import com.atl.fringe.R;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 11:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class PopupImageFragment extends DialogFragment{
    Context mContext;
    int imageId;
    public PopupImageFragment(int imageId) {
        mContext = getActivity();
        this.imageId=imageId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        View view = inflater.inflate(R.layout.frag_photo_popup, container);
        ImageView image = (ImageView)view.findViewById(R.id.popupimage);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        image.setImageResource(imageId);
        return view;
    }
}
