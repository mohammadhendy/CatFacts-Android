package com.mohammadhendy.catfacts.base.mvp;

/**
 * Created by mohammadhendy on 11/13/17.
 */

public interface View {
    void showLoading();
    void hideLoading();
    void displayErrorMessage(String message);
}
