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
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created with IntelliJ IDEA.
 * User: Sehoon
 * Date: 11/9/13
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoHolderFragment extends Fragment {
    String imageUrl;

    public static PhotoHolderFragment newInstance(String imageUrl) {
        PhotoHolderFragment fragment = new PhotoHolderFragment();

        Bundle args = new Bundle();
        args.putString("id", imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);

        Bundle args = getArguments();
        if (args != null) {
            imageUrl = args.getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.view_photo, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.view_photo_iv_image);


        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(imageUrl, imageView);

        return view;
    }
}
