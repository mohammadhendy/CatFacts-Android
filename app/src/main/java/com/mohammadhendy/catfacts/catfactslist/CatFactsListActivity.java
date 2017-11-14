package com.mohammadhendy.catfacts.catfactslist;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.mohammadhendy.catfacts.R;
import com.mohammadhendy.catfacts.base.activity.BaseListActivity;
import com.mohammadhendy.catfacts.base.adapter.BaseRecyclerViewAdapter;
import com.mohammadhendy.catfacts.base.mvp.BasePresenter;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.catfactslist.adapter.CatFactsListAdapter;
import com.mohammadhendy.catfacts.model.api.DefaultApiConfig;
import com.mohammadhendy.catfacts.model.core.CatFact;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class CatFactsListActivity extends BaseListActivity<CatFact,
        CatFactsListView, CatFactsListPresenter> implements CatFactsListView {

    @BindView(R.id.super_recycler_view)
    SuperRecyclerView superRecyclerView;

    @Override
    protected RecyclerView getRecyclerView() {
        return superRecyclerView.getRecyclerView();
    }

    @Override
    protected SuperRecyclerView getSuperRecyclerView() {
        return superRecyclerView;
    }

    @Override
    protected BaseRecyclerViewAdapter createAdapter() {
        return new CatFactsListAdapter(position -> shareFact(getRecyclerViewAdapter().getItem(position)));
    }

    private void shareFact(CatFact catFact) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_factslist;
    }

    @Override
    protected CatFactsListPresenter createPresenter(PresenterDependencies presenterDependencies) {
        return new CatFactsListPresenter(this, presenterDependencies, getApiClient(DefaultApiConfig.getInstance()));
    }

}
