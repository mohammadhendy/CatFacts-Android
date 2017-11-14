package com.mohammadhendy.catfacts.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mohammadhendy.catfacts.base.Utils.ErrorHandler;
import com.mohammadhendy.catfacts.base.model.api.PaginatedList;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.model.api.ListResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public abstract class BaseListPresenter<E, V extends BaseListView<E>> extends BasePresenter<V> {

    protected int currentPage = 1;
    // Function to map paginated list to list of data items and also check if there are more pages to load
    protected Function<PaginatedList<E>, List<E>> mapToList = response -> {
        boolean lastPage = response.getData().size() < response.getItemsPerPage();
        currentPage = response.getCurrentPage();
        if (getView() != null) {
            if (lastPage)
                getView().disableLoadingMore();
            else
                getView().enableLoadingMore();
        }
        return response.getData();
    };

    public BaseListPresenter(V view, PresenterDependencies presenterDependencies) {
        super(view, presenterDependencies);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        super.onCreate(savedInstance);
        fetchData();
    }

    /**
     * Fetch next page from server and update view
     */
    public void fetchMoreData() {
        getFetchMoreDataObservable(currentPage+1)
                .map(mapToList)
                .compose(applyLifecycleBinding())
                .compose(applySchedulers())
                .doOnSubscribe(__ -> getView().preLoadingMore())
                .doAfterTerminate(() -> getView().postLoadingMore())
                .subscribe(list -> {
                    getView().moreDataFetched(list);
                    getView().postLoadingMore();
                }, new ErrorHandler(getView()));
    }

    /**
     * Fetch first page from server and update view
     */
    public void fetchData() {
        getFetchDataObservable()
                .map(mapToList)
                .compose(applyLifecycleBinding())
                .compose(applySchedulers())
                .doOnSubscribe(__ -> getView().preLoading())
                .doAfterTerminate(() -> getView().postLoading())
                .subscribe(list -> {
                    getView().dataFetched(list);
                    getView().postLoading();
                }, new ErrorHandler(getView()));
    }

    public abstract Observable<? extends PaginatedList<E>> getFetchMoreDataObservable(int page);
    public abstract Observable<? extends PaginatedList<E>> getFetchDataObservable();
}
