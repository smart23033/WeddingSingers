package com.weddingsingers.wsapp.data.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.weddingsingers.wsapp.MyApplication;
import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class SearchResultViewHolder extends RecyclerView.ViewHolder{


    Context context = MyApplication.getContext();


    @BindView(R.id.view_search_result_iv_thumbnail)
    public YouTubeThumbnailView thumbnailImageView;

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
    String url;

    public String getUrl() {
        return url;
    }

    public void setSearchResult(SearchResult searchResult){
        this.searchResult = searchResult;

        url = searchResult.getThumbnail().substring(searchResult.getThumbnail().indexOf('=') + 1);

        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .error(ContextCompat.getDrawable(context, R.drawable.video_img_default_thumbnail))
                .into(thumbnailImageView);

        titleView.setText(searchResult.getTitle());
        singerNameView.setText(searchResult.getSingerName());
        dateView.setText(searchResult.getDate());
        hitView.setText("" + searchResult.getHit());
        favoriteView.setText("" + searchResult.getFavorite());
    }
}
