package com.mohammadhendy.catfacts.catfactslist.adapter;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.mohammadhendy.catfacts.R;
import com.mohammadhendy.catfacts.base.adapter.BaseViewHolder;
import com.mohammadhendy.catfacts.model.core.CatFact;

import butterknife.BindView;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class CatFactViewHolder extends BaseViewHolder<CatFact> {

    @BindView(R.id.list_item_catfact_fact_tv)
    TextView listItemCatfactFactTv;
    @BindView(R.id.list_item_catfact_share_btn)
    ImageButton listItemCatfactShareBtn;

    public CatFactViewHolder(ViewGroup parent, @LayoutRes int resLayout, ShareClickListener shareClickListener) {
        super(parent, resLayout);
        RxView.clicks(listItemCatfactShareBtn).subscribe(__ -> shareClickListener.shareClicked(getAdapterPosition()));
    }

    @Override
    public void bind(CatFact item) {
        listItemCatfactFactTv.setText(item.getFact());
    }
}
