package com.mohammadhendy.catfacts.base.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by mohammadhendy on 11/15/17.
 */

public class BitmapUtils {

    /**
     * Render view on bitmap, use the view bacjground drawable if it has one or just use a white color as background
     * @param view
     * @param height
     * @param width
     * @return Bitmap of the view
     */
    public static Bitmap getBitmapFromView(View view, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }

}
