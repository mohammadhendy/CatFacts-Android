package com.mohammadhendy.catfacts.base.mvp;

import java.util.List;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public interface BaseListView<E> extends View {

    void preLoading();
    void postLoading();
    void preLoadingMore();
    void postLoadingMore();
    void disableLoadingMore();
    void enableLoadingMore();

    void dataFetched(List<E> list);
    void moreDataFetched(List<E> list);

}
