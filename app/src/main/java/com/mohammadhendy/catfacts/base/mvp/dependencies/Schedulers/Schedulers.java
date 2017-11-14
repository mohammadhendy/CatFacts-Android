package com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers;

import io.reactivex.Scheduler;

/**
 * Created by mohammadhendy on 11/13/17.
 */

public interface Schedulers {

    Scheduler getIOScheduler();

    Scheduler getImmediateScheduler();

    Scheduler getMainThread();

}
