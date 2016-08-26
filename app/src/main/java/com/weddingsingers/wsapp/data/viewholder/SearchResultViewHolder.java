package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class SearchResultViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.view_search_result_iv_thumbnail)
    ImageView thumbnailImageView;

    @BindView(R.id.view_search_result_tv_title)
    TextView titleTextView;

    @BindView(R.id.view_search_result_tv_singer_name)
    TextView singerNameTextView;

    @BindView(R.id.view_search_result_tv_date)
    TextView dateTextView;

    @BindView(R.id.view_search_result_tv_hit)
    TextView hitTextView;

    @BindView(R.id.view_search_result_tv_num_of_favorite)
    TextView favoriteTextView;


    public interface OnSearchResultItemClickListener{
        public void onSearchResultItemClick(View view, SearchResult searchResult, int position);
    }

    OnSearchResultItemClickListener listener;
    public void setOnSearchResultItemClickListener(OnSearchResultItemClickListener listener){
        this.listener = listener;
    }

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onSearchResultItemClick(view,searchResult,getAdapterPosition());
                }
            }
        });
    }

    SearchResult searchResult;

    public void setSearchResult(SearchResult searchResult){
        this.searchResult = searchResult;
//        thumbnailImageView.setImageDrawable();
        titleTextView.setText(searchResult.getTitle());
        singerNameTextView.setText(searchResult.getSingerName());
        dateTextView.setText(searchResult.getDate());
        hitTextView.setText("" + searchResult.getHit());
        favoriteTextView.setText("" + searchResult.getFavorite());
    }
}
