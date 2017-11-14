package com.mohammadhendy.catfacts.catfactslist;

import com.mohammadhendy.catfacts.base.model.api.PaginatedList;
import com.mohammadhendy.catfacts.base.mvp.BaseListPresenter;
import com.mohammadhendy.catfacts.base.mvp.BaseListView;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.model.api.CatFactsApiClient;
import com.mohammadhendy.catfacts.model.core.CatFact;

import io.reactivex.Observable;

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

    @Override
    public Observable<? extends PaginatedList<CatFact>> getFetchMoreDataObservable(int page) {
        return catFactsApiClient.fetchFacts(LIMIT, page, null);
    }

    @Override
    public Observable<? extends PaginatedList<CatFact>> getFetchDataObservable() {
        return catFactsApiClient.fetchFacts(LIMIT, null, null);
    }
}
