package com.mohammadhendy.catfacts.base.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by mohammadhendy on 11/13/17.
 */

public interface Presenter {
    void onCreate(@Nullable Bundle savedInstance);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onSavedInstanceState(@NonNull Bundle savedInstance);
    void onRestoreInstanceState(@NonNull Bundle savedInstance);
}
