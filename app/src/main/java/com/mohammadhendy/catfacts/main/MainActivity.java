package com.mohammadhendy.catfacts.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mohammadhendy.catfacts.R;
import com.mohammadhendy.catfacts.base.activity.BaseActivity;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.model.api.CatFactsApiClient;
import com.mohammadhendy.catfacts.model.api.DefaultApiConfig;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.activity_main_slider_hint_tv)
    TextView activityMainSliderHintTv;
    @BindView(R.id.activity_main_length_slider)
    AppCompatSeekBar activityMainLengthSlider;
    @BindView(R.id.list_item_catfact_fact_tv)
    TextView listItemCatfactFactTv;
    @BindView(R.id.list_item_catfact_share_btn)
    ImageButton listItemCatfactShareBtn;
    @BindView(R.id.activity_main_slider_selected_value_tv)
    TextView activityMainSliderSelectedValueTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter(PresenterDependencies presenterDependencies) {
        CatFactsApiClient catFactsApiClient = getApiClient(DefaultApiConfig.getInstance());
        return new MainPresenter(this, presenterDependencies, catFactsApiClient);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        activityMainSliderHintTv.setText(getString(R.string.min_and_max,
                MainPresenter.MIN_SLIDER_LENGTH,
                MainPresenter.MAX_SLIDER_LENGTH));
        activityMainLengthSlider.setMax(MainPresenter.MAX_SLIDER_LENGTH - MainPresenter.MIN_SLIDER_LENGTH);
        getPresenter().bindSliderChanges(RxSeekBar.changes(activityMainLengthSlider));
        activityMainLengthSlider.setProgress(100);//DefaultValue
    }

    @Override
    public void displayFact(String fact) {
        listItemCatfactFactTv.setText(fact);
    }

    @Override
    public void updateSliderSelected(Integer value) {
        int correctedValue = value + MainPresenter.MIN_SLIDER_LENGTH;
        activityMainSliderSelectedValueTv.setText(String.valueOf(correctedValue));
    }

}
