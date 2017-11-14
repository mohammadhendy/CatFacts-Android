package com.mohammadhendy.catfacts.base.Utils;

import android.content.Context;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.mohammadhendy.catfacts.R;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public class DialogUtils {

    public static MaterialDialog.Builder getBaseDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .cancelable(false)
                .theme(Theme.LIGHT)
                .widgetColorRes(R.color.colorAccent)
                .titleColorRes(R.color.colorAccent)
                .contentColorRes(R.color.colorAccent)
                .positiveColorRes(R.color.colorAccent)
                .negativeColorRes(R.color.colorAccent);
    }

    public static MaterialDialog.Builder getBaseProgressDialog(Context context) {
        return getBaseDialog(context)
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .cancelable(false);
    }

    public static MaterialDialog.Builder getDialog(Context context, @StringRes int titleId, @StringRes int contentId, @StringRes int okId, @StringRes int cancelId) {
        return getBaseDialog(context)
                .title(titleId)
                .content(contentId)
                .positiveText(okId)
                .negativeText(cancelId);
    }

    public static MaterialDialog.Builder getDialog(Context context, String title, String content, String buttonText) {
        return getBaseDialog(context)
                .title(title)
                .content(content)
                .positiveText(buttonText);
    }

    public static MaterialDialog.Builder getProgressDialog(Context context, @StringRes int titleId, @StringRes int contentId) {
        return getBaseProgressDialog(context)
                .title(titleId)
                .content(contentId);
    }

    public static MaterialDialog.Builder getProgressDialog(Context context, String titleId, String contentId) {
        return getBaseProgressDialog(context)
                .title(titleId)
                .content(contentId);
    }

    public static MaterialDialog.Builder getDefaultProgressDialog(Context context, String content) {
        return getBaseProgressDialog(context)
                .title(R.string.msg_please_wait)
                .content(content);
    }

    public static MaterialDialog.Builder getDefaultProgressDialog(Context context) {
        return getBaseProgressDialog(context)
                .title(R.string.msg_please_wait);
    }

    public static MaterialDialog.Builder getDefaultProgressDialog(Context context, @StringRes int contentId) {
        return getBaseProgressDialog(context)
                .title(R.string.msg_please_wait)
                .content(contentId);
    }

}
