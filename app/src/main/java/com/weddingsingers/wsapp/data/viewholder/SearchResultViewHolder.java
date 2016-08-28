package com.weddingsingers.wsapp.data.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class SearchResultViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.view_search_result_iv_thumbnail)
    ImageView thumbnailImageView;

    @BindView(R.id.view_search_result_tv_title)
    TextView titleView;

    @BindView(R.id.view_search_result_tv_singer_name)
    TextView singerNameView;

    @BindView(R.id.view_search_result_tv_date)
    TextView dateView;

    @BindView(R.id.view_search_result_tv_hit)
    TextView hitView;

    @BindView(R.id.view_search_result_tv_num_of_favorite)
    TextView favoriteView;


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
        titleView.setText(searchResult.getTitle());
        singerNameView.setText(searchResult.getSingerName());
        dateView.setText(searchResult.getDate());
        hitView.setText("" + searchResult.getHit());
        favoriteView.setText("" + searchResult.getFavorite());
    }
}
