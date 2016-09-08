package com.iaccept.pehechano.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iaccept.pehechano.R;

import java.util.ArrayList;

public class DrawerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list;

    private int[] imageArray = {R.drawable.ic_settings, R.drawable.ic_settings, R.drawable.ic_settings, R.drawable.ic_settings, R.drawable.ic_settings};

    public DrawerListAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_drawer_list_item, null);
            holder = new ViewHolder();
            holder.textViewSpinner = (TextView) convertView.findViewById(R.id.textViewDrawerList);
            holder.image = (ImageView) convertView.findViewById(R.id.imagesDrawer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewSpinner.setText(String.valueOf(getItem(position)));
        holder.image.setImageResource(imageArray[position]);
        return convertView;
    }

    static class ViewHolder {
        public TextView textViewSpinner;
        public ImageView image;
    }
}