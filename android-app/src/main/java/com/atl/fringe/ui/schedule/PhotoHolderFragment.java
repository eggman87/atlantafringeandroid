package com.atl.fringe.ui.schedule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.atl.fringe.R;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_photo, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.view_photo_iv_image);
        imageView.setImageResource(imageId);

        return view;
    }
}
