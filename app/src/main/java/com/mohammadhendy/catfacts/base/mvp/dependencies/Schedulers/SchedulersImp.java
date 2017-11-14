package com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by mohammadhendy on 11/13/17.
 */

public class SchedulersImp implements Schedulers {

    @Override
    public Scheduler getIOScheduler() {
        return io.reactivex.schedulers.Schedulers.io();
    }

    @Override
    public Scheduler getImmediateScheduler() {
        return io.reactivex.schedulers.Schedulers.trampoline();
    }

    @Override
    public Scheduler getMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
