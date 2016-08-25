package com.weddingsingers.wsapp.main.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SJSJ on 2016-07-28.
 */
public class MainAdapter extends RecyclerView.Adapter<TextViewHolder> {
    List<String> items = new ArrayList<>();

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        holder.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
