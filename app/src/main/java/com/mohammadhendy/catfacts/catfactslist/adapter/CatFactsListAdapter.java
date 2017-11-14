package com.mohammadhendy.catfacts.catfactslist.adapter;

import android.view.ViewGroup;

import com.mohammadhendy.catfacts.R;
import com.mohammadhendy.catfacts.base.adapter.BaseRecyclerViewAdapter;
import com.mohammadhendy.catfacts.model.core.CatFact;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class CatFactsListAdapter extends BaseRecyclerViewAdapter<CatFact, CatFactViewHolder>{

    private ShareClickListener shareClickListener;

    public CatFactsListAdapter(ShareClickListener shareClickListener) {
        super();
        this.shareClickListener = shareClickListener;
    }

    @Override
    public CatFactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatFactViewHolder(parent, R.layout.list_item_catfact, shareClickListener);
    }

    @Override
    public void onBindViewHolder(CatFactViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
