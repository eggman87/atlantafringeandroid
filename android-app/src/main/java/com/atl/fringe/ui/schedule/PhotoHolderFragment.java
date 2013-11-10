package com.atl.fringe.ui.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.atl.fringe.ui.schedule.fragment.PopupImageFragment;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoHolderFragment extends Fragment {
    int imageId;

    public static PhotoHolderFragment newInstance(int imageId) {
        PhotoHolderFragment fragment = new PhotoHolderFragment();

        Bundle args = new Bundle();
        args.putInt("id", imageId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);

        Bundle args = getArguments();
        if (args != null) {
            imageId = args.getInt("id");
        }
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
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                PopupImageFragment testDialog = new PopupImageFragment(imageId);
                testDialog.setRetainInstance(true);
                testDialog.show(fm, "fragment_name");
            }
        });
        return layout;
    }
}
