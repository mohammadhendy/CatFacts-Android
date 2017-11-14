package com.mohammadhendy.catfacts.base.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

/**
 * Created by mohammadhendy on 11/13/17.
 */

public abstract class BasePresenter<V extends View> implements Presenter {

    private V view;
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
    private Schedulers schedulers;

    public BasePresenter(V view, Schedulers schedulers) {
        Timber.tag(BasePresenter.class.getSimpleName());
        this.view = view;
        this.schedulers = schedulers;
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstance) {
        Timber.d("onCreate");
        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @CallSuper
    @Override
    public void onStart() {
        Timber.d("onStart");
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @CallSuper
    @Override
    public void onResume() {
        Timber.d("onResume");
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @CallSuper
    @Override
    public void onPause() {
        Timber.d("onPause");
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @CallSuper
    @Override
    public void onStop() {
        Timber.d("onStop");
        lifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        Timber.d("onDestroy");
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
    }

    @CallSuper
    @Override
    public void onSavedInstanceState(@NonNull Bundle savedInstance) {
        Timber.d("onSavedInstanceState");
    }

    @CallSuper
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstance) {
        Timber.d("onRestoreInstanceState");
    }

    protected Observable<ActivityEvent> lifeCycle() {
        return lifecycleSubject;
    }

    /**
     * Apply activity lifecycle on observable subscription
     * Used to make sure of observable subscriptions are automatically disposed when activity destroyed
     * @param <O>
     * @return Transformed Observable that is bound Activity Lifecycle
     */
    protected <O> ObservableTransformer<O, O> applyLifecycleBinding() {
        return upstream -> upstream.compose(RxLifecycleAndroid.bindActivity(lifeCycle()));
    }

    /**
     * Apply show/hide loading on observables subscription
     * Used to automatically show loading progress on observable subscription and hide it after observable terminated
     * @param <O>
     * @return
     */
    protected <O> ObservableTransformer<O, O> applyLoadingBehaviour() {
        return upstream -> upstream
                .doOnSubscribe(__ -> view.showLoading())
                .doAfterTerminate(() -> view.hideLoading());
    }

    /**
     * Apply schedulers to observable subscription
     * Used to execute on io thread and observe result on main thread
     * @param <O>
     * @return
     */
    protected <O> ObservableTransformer<O, O> applySchedulers() {
        return upstream -> upstream
                .subscribeOn(schedulers.getIOScheduler())
                .observeOn(schedulers.getMainThread());
    }

}
