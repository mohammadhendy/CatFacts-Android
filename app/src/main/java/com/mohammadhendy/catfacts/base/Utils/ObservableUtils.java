package com.mohammadhendy.catfacts.base.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Single;

/**
 * Created by mohammadhendy on 11/15/17.
 */

public class ObservableUtils {

    /**
     * Create Single Observable that compress an image into PNG format then save it to pictures files directory
     * @param context
     * @param bitmap
     * @param imageName just name without extension
     * @return File where new images is saved
     */
    public static Single<File> createSaveImageObservable(final Context context, final Bitmap bitmap, final String imageName) {
        return Single.create(e -> {
            File path = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), imageName + ".png");
            try {
                FileOutputStream fos = new FileOutputStream(path);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
                e.onSuccess(path);
            } catch (Exception ex) {
                e.onError(ex);
            }
        });
    }
}
