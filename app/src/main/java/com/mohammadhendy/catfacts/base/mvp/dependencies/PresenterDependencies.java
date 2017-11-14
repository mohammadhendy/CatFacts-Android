package com.mohammadhendy.catfacts.base.mvp.dependencies;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers.Schedulers;
import com.mohammadhendy.catfacts.base.mvp.dependencies.SharedPrefs.SharedPrefs;

/**
 * Created by mohammadhendy on 11/14/17.
 */

/**
 * Common presenter dependencies like Scheduler, and Shared Preferences
 */
public class PresenterDependencies {
    @NonNull
    private Schedulers schedulers;
    @NonNull
    private SharedPrefs sharedPrefs;

    public PresenterDependencies(@NonNull Schedulers schedulers, @NonNull SharedPrefs sharedPrefs) {
        this.schedulers = schedulers;
        this.sharedPrefs = sharedPrefs;
    }

    public Schedulers getSchedulers() {
        return schedulers;
    }

    @NonNull
    public SharedPrefs getSharedPrefs() {
        return sharedPrefs;
    }
}
