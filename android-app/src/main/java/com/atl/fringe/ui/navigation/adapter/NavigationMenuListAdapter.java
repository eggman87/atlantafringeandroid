package com.atl.fringe.ui.navigation.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.atl.fringe.R;
import com.atl.fringe.ui.navigation.NavigationMenuItem;

import java.util.List;

/**
 * Copyright NCR Inc,
 * User: matthewharris
 * Date: 11/9/13
 * Time: 4:28 PM
 */
public class NavigationMenuListAdapter extends BaseAdapter {

    private List<NavigationMenuItem> navigationItems;
    private Activity currentActivity;

    public NavigationMenuListAdapter(Activity currentActivity, List<NavigationMenuItem> navigationItems) {
        this.currentActivity = currentActivity;
        this.navigationItems = navigationItems;
    }

    @Override
    public int getCount() {
        return navigationItems.size();
    }

    @Override
    public NavigationMenuItem getItem(int position) {
        return navigationItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.view_list_nav_menu, null);

            viewHolder = new ViewHolder();
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.view_list_nav_iv_icon);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.view_list_nav_tv_title);
            viewHolder.txtSubTitle = (TextView) convertView.findViewById(R.id.view_list_nav_tv_subtitle);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        NavigationMenuItem target = navigationItems.get(position);

        viewHolder.imgIcon.setBackgroundResource(target.icon);
        viewHolder.txtTitle.setText(target.title);
        viewHolder.txtSubTitle.setText(target.subTitle);

        return convertView;
    }

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtSubTitle;
    }
}
