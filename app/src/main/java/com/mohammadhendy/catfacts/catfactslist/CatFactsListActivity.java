package com.mohammadhendy.catfacts.catfactslist;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.mohammadhendy.catfacts.R;
import com.mohammadhendy.catfacts.base.Utils.BitmapUtils;
import com.mohammadhendy.catfacts.base.Utils.ObservableUtils;
import com.mohammadhendy.catfacts.base.Utils.SharingUtils;
import com.mohammadhendy.catfacts.base.activity.BaseListActivity;
import com.mohammadhendy.catfacts.base.adapter.BaseRecyclerViewAdapter;
import com.mohammadhendy.catfacts.base.mvp.BasePresenter;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.catfactslist.adapter.CatFactsListAdapter;
import com.mohammadhendy.catfacts.model.api.DefaultApiConfig;
import com.mohammadhendy.catfacts.model.core.CatFact;

import java.io.File;

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
    protected void initViews(@Nullable Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

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
        return new CatFactsListAdapter((view) -> shareFact(view));
    }

    private void shareFact(View view) {
        Bitmap factBitmap = BitmapUtils.getBitmapFromView(view, view.getWidth(), view.getHeight());
        getPresenter().shareFactImage(ObservableUtils.createSaveImageObservable(this, factBitmap, "temp_image"));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_factslist;
    }

    @Override
    protected CatFactsListPresenter createPresenter(PresenterDependencies presenterDependencies) {
        return new CatFactsListPresenter(this, presenterDependencies, getApiClient(DefaultApiConfig.getInstance()));
    }

    @Override
    public void share(File file) {
        SharingUtils.shareImage(this, file);
    }
}
