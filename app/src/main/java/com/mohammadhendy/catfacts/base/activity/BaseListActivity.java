package com.mohammadhendy.catfacts.base.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.mohammadhendy.catfacts.base.adapter.BaseRecyclerViewAdapter;
import com.mohammadhendy.catfacts.base.mvp.BaseListPresenter;
import com.mohammadhendy.catfacts.base.mvp.BaseListView;

import java.util.List;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public abstract class BaseListActivity<E, V extends BaseListView<E>, P extends BaseListPresenter<E, V>>
        extends BaseActivity<P> implements BaseListView<E>, OnMoreListener, SwipeRefreshLayout.OnRefreshListener {

    BaseRecyclerViewAdapter<E, ? extends RecyclerView.ViewHolder> recyclerViewAdapter;

    public void setRecyclerViewAdapter(BaseRecyclerViewAdapter<E, ? extends RecyclerView.ViewHolder> recyclerViewAdapter) {
        this.recyclerViewAdapter = recyclerViewAdapter;
    }

    public BaseRecyclerViewAdapter<E, ? extends RecyclerView.ViewHolder> getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    @CallSuper
    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setRecyclerViewAdapter(createAdapter());
        getRecyclerView().setLayoutManager(createRecyclerViewLayoutManager());
        getRecyclerView().setAdapter(getRecyclerViewAdapter());
    }

    protected RecyclerView.LayoutManager createRecyclerViewLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public void preLoading() {
        if (getRecyclerViewAdapter() != null &&
                getRecyclerViewAdapter().getItems() != null &&
                getRecyclerViewAdapter().getItemCount() != 0) {
            getSuperRecyclerView().setRefreshing(true);
        } else {
            getSuperRecyclerView().showProgress();
            getSuperRecyclerView().getSwipeToRefresh().setEnabled(false);
            getSuperRecyclerView().getSwipeToRefresh().setRefreshing(false);
        }
    }

    @Override
    public void postLoading() {
        getSuperRecyclerView().showRecycler();
        getSuperRecyclerView().setRefreshListener(this);
        if (getSuperRecyclerView().getSwipeToRefresh().isRefreshing())
            getSuperRecyclerView().getSwipeToRefresh().setRefreshing(false);
    }

    @Override
    public void preLoadingMore() {
        getSuperRecyclerView().showMoreProgress();
    }

    @Override
    public void postLoadingMore() {
        getSuperRecyclerView().hideMoreProgress();
    }

    @Override
    public void enableLoadingMore() {
        getSuperRecyclerView().setupMoreListener(this, 2);
        getSuperRecyclerView().setLoadingMore(false);
    }

    @Override
    public void disableLoadingMore() {
        getSuperRecyclerView().removeMoreListener();
    }

    @Override
    public void dataFetched(List<E> list) {
        getRecyclerViewAdapter().swapItems(list);
    }

    @Override
    public void moreDataFetched(List<E> list) {
        getRecyclerViewAdapter().addAll(list);
        getSuperRecyclerView().hideMoreProgress();
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        getPresenter().fetchMoreData();
    }

    @Override
    public void onRefresh() {
        getPresenter().fetchData();
    }

    protected abstract RecyclerView getRecyclerView();
    protected abstract SuperRecyclerView getSuperRecyclerView();
    protected abstract BaseRecyclerViewAdapter createAdapter();
}
