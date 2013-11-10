package com.atl.fringe.ui.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoHolderFragment extends Fragment {
    int imageId;

    public PhotoHolderFragment(int imageId){
        this.imageId=imageId;
    }

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView image = new ImageView(getActivity());
        image.setImageResource(imageId);

        LinearLayout layout = new LinearLayout(getActivity());
        //layout.setLayoutParams(new LinearLayout.LayoutParams());

        layout.setGravity(Gravity.CENTER);
        layout.addView(image);

        return layout;
    }
}
