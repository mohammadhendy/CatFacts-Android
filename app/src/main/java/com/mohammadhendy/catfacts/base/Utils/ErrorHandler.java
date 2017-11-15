package com.mohammadhendy.catfacts.base.Utils;

import com.mohammadhendy.catfacts.base.mvp.View;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class ErrorHandler implements Consumer<Throwable> {

    private View view;
    public ErrorHandler(View view) {
        this.view = view;
    }

    @Override
    public void accept(Throwable throwable) {
        Timber.d(throwable);
        if (throwable.getMessage() != null)
            view.displayErrorMessage(throwable.getMessage());
        else
            view.displayErrorMessage("Exception: while handling observable");
    }
}
