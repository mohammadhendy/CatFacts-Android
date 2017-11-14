package com.mohammadhendy.catfacts.base.di.components;

import android.support.annotation.NonNull;

import com.mohammadhendy.catfacts.base.di.modules.PresenterDependenciesModule;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;

import dagger.Component;

/**
 * Created by mohammadhendy on 11/14/17.
 */

@Component(modules = PresenterDependenciesModule.class, dependencies = {SchedulersComponent.class, SharedPrefsComponent.class})
public interface PresenterDependenciesComponent {

    @NonNull
    PresenterDependencies presenterDependencies();
}
