package com.weddingsingers.wsapp.function.video.singerreview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Review;
import com.weddingsingers.wsapp.data.viewholder.SingerReviewViewHolder;
import com.weddingsingers.wsapp.data.viewholder.VideoListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-09-02.
 */
public class SingerReviewAdapter extends RecyclerView.Adapter<SingerReviewViewHolder> {

    List<Review> items = new ArrayList<>();
    int type = 0;

    public void add(Review review) {
        items.add(review);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public SingerReviewAdapter(int type) {
        this.type = type;
    }

    @Override
    public SingerReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_singer_review, parent,false);
        SingerReviewViewHolder holder = new SingerReviewViewHolder(view, type);
//        holder.setOnVideoListItemClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(SingerReviewViewHolder holder, int position) {
        holder.setReview(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
