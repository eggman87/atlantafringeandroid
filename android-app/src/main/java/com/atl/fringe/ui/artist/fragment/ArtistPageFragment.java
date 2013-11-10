package com.atl.fringe.ui.artist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.atl.fringe.R;
import com.fringe.datacontract.Artist;
import com.nostra13.universalimageloader.core.ImageLoader;
import roboguice.inject.InjectView;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/10/13
 * Time: 12:53 PM
 */
public class ArtistPageFragment extends Fragment {

    @InjectView(R.id.frag_artist_page_iv_artist)ImageView imageArtist;
    @InjectView(R.id.frag_artist_page_tv_artist_name)TextView txtArtistName;

    private Artist targetArtist;

    public static final String ARGS_ARTIST = "artist:args";

    public static ArtistPageFragment newInstance(Artist artist) {
        Bundle args = new Bundle();
        args.putParcelable(ARGS_ARTIST, artist);
        ArtistPageFragment fragment = new ArtistPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            targetArtist = args.getParcelable(ARGS_ARTIST);

            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(targetArtist.photos.get(0).url, imageArtist);

            txtArtistName.setText(targetArtist.stageName);
        }
    }
}
