package com.mohammadhendy.catfacts.catfactslist;

import com.mohammadhendy.catfacts.base.Utils.ErrorHandler;
import com.mohammadhendy.catfacts.base.model.api.PaginatedList;
import com.mohammadhendy.catfacts.base.mvp.BaseListPresenter;
import com.mohammadhendy.catfacts.base.mvp.BaseListView;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.model.api.CatFactsApiClient;
import com.mohammadhendy.catfacts.model.core.CatFact;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class CatFactsListPresenter extends BaseListPresenter<CatFact, CatFactsListView> {

    public static final int LIMIT = 10;

    private CatFactsApiClient catFactsApiClient;

    public CatFactsListPresenter(CatFactsListView view, PresenterDependencies presenterDependencies, CatFactsApiClient catFactsApiClient) {
        super(view, presenterDependencies);
        this.catFactsApiClient = catFactsApiClient;
    }

    /**
     * Get observable to fetch more catfacts data from server when user scrolls list down
     * @param page page to fetch
     * @return Observable of CatFact
     */
    @Override
    public Observable<? extends PaginatedList<CatFact>> getFetchMoreDataObservable(int page) {
        return catFactsApiClient.fetchFacts(LIMIT, page, null);
    }

    /**
     * Get Observable to fetch cat facts from server when reloading list
     * @return Observable of CatFact
     */
    @Override
    public Observable<? extends PaginatedList<CatFact>> getFetchDataObservable() {
        return catFactsApiClient.fetchFacts(LIMIT, null, null);
    }

    /**
     * Compress and save bitmap of fact text then share it using native intent
     * @param imageObservable
     */
    public void shareFactImage(Single<File> imageObservable) {
        imageObservable
                .subscribeOn(getSchedulers().getIOScheduler())
                .observeOn(getSchedulers().getMainThread())
                .doOnSubscribe(__ -> getView().showLoading())
                .doAfterTerminate(() -> getView().hideLoading())
                .subscribe(file -> getView().share(file), new ErrorHandler(getView()));
    }
}
