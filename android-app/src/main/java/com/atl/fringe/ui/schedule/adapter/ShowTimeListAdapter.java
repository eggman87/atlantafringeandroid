package com.atl.fringe.ui.schedule.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.atl.fringe.R;
import com.fringe.datacontract.Photo;
import com.fringe.datacontract.ShowTime;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 2:32 PM
 */
public class ShowTimeListAdapter extends BaseAdapter {

    private List<ShowTime> showTimes;
    private ItemClickListener itemClickListener;

    public ShowTimeListAdapter(List<ShowTime> showTimes, ItemClickListener itemClickListener) {
        this.showTimes = showTimes;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getCount() {
        return showTimes.size();
    }

    @Override
    public ShowTime getItem(int position) {
        return showTimes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.view_list_showtime, null);

            viewHolder = new ViewHolder();
            viewHolder.imageBackground = (ImageView) convertView.findViewById(R.id.view_list_showtime_iv_bg);
            viewHolder.txtTime = (TextView) convertView.findViewById(R.id.view_list_st_tv_time);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.view_list_st_tv_title);
            viewHolder.txtSubTitle = (TextView) convertView.findViewById(R.id.view_list_st_tv_sub_title);
            viewHolder.imageAdd = (ImageView) convertView.findViewById(R.id.view_list_st_btn_fav);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        final ShowTime showTime = showTimes.get(position);

        Calendar time = showTime.startTime;
        SimpleDateFormat format = new SimpleDateFormat("h:mm aa");
        viewHolder.txtTime.setText(format.format(time.getTime()));
        viewHolder.txtTitle.setText(showTime.show.title);
        viewHolder.txtSubTitle.setText(showTime.show.artist.stageName +" @ " + showTime.venue.name);

        viewHolder.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemAddClick(showTime);
            }
        });

        Photo photoToUse = showTime.venue.photos.get(0);
        ImageLoader.getInstance().displayImage(photoToUse.url, viewHolder.imageBackground);

        return convertView;
    }

    public interface ItemClickListener {
        void onItemAddClick(ShowTime showTime);
    }


    static class ViewHolder {
        ImageView imageBackground;
        ImageView imageAdd;
        TextView txtTime;
        TextView txtTitle;
        TextView txtSubTitle;
    }
}
