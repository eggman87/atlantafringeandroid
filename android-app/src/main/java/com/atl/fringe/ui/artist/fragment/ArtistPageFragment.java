package com.atl.fringe.ui.artist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.atl.fringe.R;
import com.atl.fringe.ui.BaseFragment;
import com.fringe.datacontract.Artist;
import com.nostra13.universalimageloader.core.ImageLoader;
import roboguice.inject.InjectView;

import java.util.Random;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/10/13
 * Time: 12:53 PM
 */
public class ArtistPageFragment extends BaseFragment {

    @InjectView(R.id.frag_artist_page_iv_artist)ImageView imageArtist;
    @InjectView(R.id.frag_artist_page_tv_artist_name)TextView txtArtistName;
    @InjectView(R.id.frag_artist_page_tv_artists_bio)TextView txtArtistBio;

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
        return inflater.inflate(R.layout.frag_artist_page, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            targetArtist = args.getParcelable(ARGS_ARTIST);

            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(targetArtist.photos.get(new Random().nextInt(8)).url, imageArtist);

            txtArtistName.setText(targetArtist.stageName);
            txtArtistBio.setText(targetArtist.description);
        }
    }
}
