package com.weddingsingers.wsapp.function.video.reservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class ReservationSpinnerAdapter extends BaseAdapter {
    List<String> items = new ArrayList<>();

    public void addAll(ArrayList<String> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void add(String item){
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view;
        if(convertView == null){
            view = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        }else{
            view = (TextView)convertView;
        }
        view.setText(items.get(position));
        return view;
    }
}
