package com.weddingsingers.wsapp.function.search.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.data.viewholder.SingerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SingerViewHolder>{
    List<Singer> items = new ArrayList<>();

    public void add(Singer singer){
        items.add(singer);
        notifyDataSetChanged();
    }

    @Override
    public SingerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_result_recycler,parent,false);

        return new SingerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SingerViewHolder holder, int position) {
        holder.setSinger(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



}
