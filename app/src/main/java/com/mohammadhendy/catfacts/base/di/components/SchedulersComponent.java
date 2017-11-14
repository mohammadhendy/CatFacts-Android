package com.mohammadhendy.catfacts.base.di.components;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.di.modules.SchedulersModule;
import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers.Schedulers;

import dagger.Component;

/**
 * Created by mohammadhendy on 11/13/17.
 */

@Component(modules = SchedulersModule.class)
public interface SchedulersComponent {

    @NonNull
    public Schedulers schedulers();
}
