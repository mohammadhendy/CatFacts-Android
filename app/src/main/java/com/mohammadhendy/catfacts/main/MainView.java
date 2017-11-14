package com.mohammadhendy.catfacts.main;

import com.mohammadhendy.catfacts.base.mvp.View;

/**
 * Created by mohammadhendy on 11/14/17.
 */

interface MainView extends View {
    void displayFact(String fact);

    void updateSliderSelected(Integer value);
}
