package com.mohammadhendy.catfacts.main;

import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.mohammadhendy.catfacts.base.Utils.ErrorHandler;
import com.mohammadhendy.catfacts.base.mvp.BasePresenter;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.model.api.CatFactsApiClient;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;


/**
 * Created by mohammadhendy on 11/14/17.
 */

public class MainPresenter extends BasePresenter<MainView> {

    public static final int MIN_SLIDER_LENGTH = 30;
    public static final int MAX_SLIDER_LENGTH = 400;

    private CatFactsApiClient catFactsApiClient;

    public MainPresenter(MainView view, PresenterDependencies presenterDependencies, CatFactsApiClient catFactsApiClient) {
        super(view, presenterDependencies);
        this.catFactsApiClient = catFactsApiClient;
    }

    /**
     * Bind slider value changes to random fact api
     * Whenever slider value changes new random fact with max length equal to slider value is fetched
     * @param changes
     */
    public void bindSliderChanges(Observable<Integer> changes) {
        changes
                .distinctUntilChanged() // skip repeated values
                .debounce(100, TimeUnit.MILLISECONDS) // don't accept any values until 300 millis pass
                .observeOn(getSchedulers().getMainThread())
                .flatMap(value -> {
                    getView().updateSliderSelected(value);
                    return Observable.just(value);
                })
                .subscribeOn(getSchedulers().getMainThread())
                .observeOn(getSchedulers().getIOScheduler())
                .switchMap(value -> catFactsApiClient.fetchRandomFact(value + MIN_SLIDER_LENGTH)
                        //Skip exceptions and resume subscriptions
                        .onErrorResumeNext(throwable -> {
                            Timber.d(throwable);
                            return Observable.empty();
                        }))
                .compose(applyLifecycleBinding())
                .compose(applySchedulers())
                .subscribe(catFact -> {
                    getView().displayFact(catFact.getFact());
                }, new ErrorHandler(getView()));
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
