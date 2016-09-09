package com.weddingsingers.wsapp.function.search.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weddingsingers.wsapp.R;
import com.weddingsingers.wsapp.data.Search;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentSearchFragment extends Fragment {
    public RecentSearchFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.search_tv_recent)
    TextView recentView;

    @BindView(R.id.search_btn_recent)
    Button recentBtn;

    public static RecentSearchFragment newInstance(Search search){
        RecentSearchFragment fragment = new RecentSearchFragment();
        Bundle b = new Bundle();
        b.putSerializable(SearchActivity.KEY_SEARCH,search);
        fragment.setArguments(b);
        return fragment;
    }

    Search search;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            search = (Search) getArguments().getSerializable(SearchActivity.KEY_SEARCH);
            Log.i("RecentSearchFragment","search keyword : " + search.getKeyword());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_search, container, false);

        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          if(item.getItemId() == R.id.search_menu_filter) {
                item.setIcon(R.drawable.search_ic_filter_on);
                FragmentTransaction ft = getActivity().getSupportFragmentManager()
                        .beginTransaction();
                FilterFragment filterFragment = FilterFragment.newInstance(search);
                ft.replace(R.id.act_search_fl_container, filterFragment, SearchActivity.FRAG_FILTER);
                ft.commit();
            }
        return super.onOptionsItemSelected(item);
    }
}