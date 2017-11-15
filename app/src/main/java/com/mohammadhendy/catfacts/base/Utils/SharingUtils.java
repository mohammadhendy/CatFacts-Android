package com.mohammadhendy.catfacts.base.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by mohammadhendy on 11/15/17.
 */

public class SharingUtils {

    /**
     * Share an image using intent, any application that handle action_send will be listed including Facebook and Twitter if they are installed
     * Uses FileProvider for APi greater than 21
     * @param context
     * @param file
     */
    public static void shareImage(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= 22) {
            intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(context,
                    "com.mohammadhendy.catfacts.fileprovider", file));
        } else {
            Uri uri = Uri.fromFile(file);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        context.startActivity(intent);
    }

}
