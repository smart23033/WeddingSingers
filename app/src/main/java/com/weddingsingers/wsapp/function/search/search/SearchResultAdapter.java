package com.weddingsingers.wsapp.function.search.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.SearchResult;
import com.weddingsingers.wsapp.data.Singer;
import com.weddingsingers.wsapp.data.viewholder.SearchResultViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultViewHolder>
        implements SearchResultViewHolder.OnSearchResultItemClickListener{
    List<SearchResult> items = new ArrayList<>();

    public void add(SearchResult searchResult) {
        items.add(searchResult);
        notifyDataSetChanged();
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_search_result,parent,false);
        SearchResultViewHolder holder = new SearchResultViewHolder(view);
        holder.setOnSearchResultItemClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.setSearchResult(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface OnAdapterItemClickListener{
        public void onAdapterItemClick(View view, SearchResult searchResult, int position);
    }

    OnAdapterItemClickListener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onSearchResultItemClick(View view, SearchResult searchResult, int position) {
        if(listener != null){
            listener.onAdapterItemClick(view,searchResult,position);
        }
    }
}
