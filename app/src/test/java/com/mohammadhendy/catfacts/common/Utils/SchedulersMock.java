package com.mohammadhendy.catfacts.common.Utils;

import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers.Schedulers;

import io.reactivex.Scheduler;

/**
 * Created by mohammadhendy on 11/15/17.
 */

/**
 * Mock scheduler to execute all rx observables on trampoline
 */
public class SchedulersMock implements Schedulers {
    @Override
    public Scheduler getIOScheduler() {
        return io.reactivex.schedulers.Schedulers.trampoline();
    }

    @Override
    public Scheduler getImmediateScheduler() {
        return io.reactivex.schedulers.Schedulers.trampoline();
    }

    @Override
    public Scheduler getMainThread() {
        return io.reactivex.schedulers.Schedulers.trampoline();
    }
}
