package com.mohammadhendy.catfacts.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mohammadhendy.catfacts.R;
import com.mohammadhendy.catfacts.base.Utils.DialogUtils;
import com.mohammadhendy.catfacts.base.app.BaseApp;
import com.mohammadhendy.catfacts.base.di.components.SchedulersComponent;
import com.mohammadhendy.catfacts.base.mvp.BasePresenter;
import com.mohammadhendy.catfacts.base.mvp.dependencies.Schedulers;
import com.mohammadhendy.catfacts.base.mvp.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mohammadhendy on 11/13/17.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View {


    private P presenter;
    Unbinder butterKnifeUnBinder;
    private MaterialDialog materialDialog;

    public P getPresenter() {
        return presenter;
    }

    protected final SchedulersComponent getSchedulersComponent() {
        return ((BaseApp) getApplication()).
                getSchedulersComponent();
    }

    protected final Schedulers getSchedulers() {
        return getSchedulersComponent().schedulers();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayout());
        bindViews();
        presenter = createPresenter(getSchedulers());
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

        hideDialog();
        unBindViews();
    }

    protected final void bindViews() {
        butterKnifeUnBinder = ButterKnife.bind(this);
    }

    protected final void unBindViews() {
        if (butterKnifeUnBinder != null)
            butterKnifeUnBinder.unbind();
    }

    @Override
    public void showLoading() {
        showDialog(DialogUtils.getDefaultProgressDialog(this).build());
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void displayErrorMessage(String message) {
        showDialog(DialogUtils.getDialog(this, getString(R.string.error_title), message, getString(R.string.ok_text)).build());
    }

    protected void hideDialog() {
        if (materialDialog != null) {
            materialDialog.dismiss();
            materialDialog.cancel();
            materialDialog = null;
        }
    }

    protected void showDialog(MaterialDialog dialog) {
        if (materialDialog != null) {
            materialDialog.dismiss();
            materialDialog.cancel();
            materialDialog = null;
        }
        materialDialog = dialog;
        materialDialog.show();
    }

    protected MaterialDialog getMaterialDialog() {
        return materialDialog;
    }

    abstract protected @LayoutRes int getLayout();
    abstract protected P createPresenter(Schedulers schedulers);


}
