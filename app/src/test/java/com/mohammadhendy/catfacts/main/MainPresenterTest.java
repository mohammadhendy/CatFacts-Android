package com.mohammadhendy.catfacts.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.SeekBar;

import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.mohammadhendy.catfacts.BuildConfig;
import com.mohammadhendy.catfacts.base.Utils.ObservableUtils;
import com.mohammadhendy.catfacts.base.model.api.ApiClient;
import com.mohammadhendy.catfacts.base.mvp.dependencies.PresenterDependencies;
import com.mohammadhendy.catfacts.common.Utils.ObservableMockUtils;
import com.mohammadhendy.catfacts.common.di.components.DaggerPresenterDependenciesTestComponent;
import com.mohammadhendy.catfacts.model.api.CatFactsApiClient;
import com.mohammadhendy.catfacts.model.core.CatFact;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;


/**
 * Created by mohammadhendy on 11/15/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainPresenterTest {

    @Inject
    PresenterDependencies presenterDependencies;
    @Inject
    CatFactsApiClient apiClient;
    @Mock
    MainView view;


    Context context;
    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        DaggerPresenterDependenciesTestComponent.create().inject(this);

        context = RuntimeEnvironment.application.getApplicationContext();

        presenter = new MainPresenter(view, presenterDependencies, apiClient);
    }

    @Test
    public void bindSliderChangesWithLengthChangeShouldDisplayFact() throws Exception {
        //Arrange
        SeekBar slider = new SeekBar(context);
        CatFact catFact = new CatFact();
        catFact.setFact("Test fact");
        when(apiClient.fetchRandomFact(anyInt())).thenReturn(Observable.just(catFact));
        slider.setProgress(100);

        //act
        presenter.bindSliderChanges(RxSeekBar.changes(slider));

        //assert
        verify(view, timeout(200)).updateSliderSelected(eq(100));
        verify(view, timeout(200)).displayFact(eq("Test fact"));
    }

    @Test
    public void bindSliderChangesWithNetworkErrorShouldDisplayError() throws Exception {
        //Arrange
        SeekBar slider = new SeekBar(context);
        CatFact catFact = new CatFact();
        catFact.setFact("Test fact");
        when(apiClient.fetchRandomFact(anyInt())).thenReturn(ObservableMockUtils.createErrorConnectingObservableResponse());
        slider.setProgress(100);

        //act
        presenter.bindSliderChanges(RxSeekBar.changes(slider));

        //assert
        verify(view, timeout(200)).updateSliderSelected(eq(100));
        verify(view, timeout(200)).displayErrorMessage(anyString());
    }

    /**
     * This test fails but scenario in app works fine so it is an issue applying the test,
     * the issue related to simulating slider changes after 250 millis to avoid rejection by debounce and see if subscription continue or not
     */
    @Test
    public void bindSliderChangesWithErrorShouldResumeAfter() throws Exception {
        //Arrange
        SeekBar slider = new SeekBar(context);
        CatFact catFact = new CatFact();
        catFact.setFact("Test fact");
        when(apiClient.fetchRandomFact(anyInt())).thenReturn(ObservableMockUtils.createErrorConnectingObservableResponse());
        slider.setProgress(100);

        //act
        presenter.bindSliderChanges(RxSeekBar.changes(slider));
        slider.postDelayed(() -> {
            when(apiClient.fetchRandomFact(anyInt())).thenReturn(Observable.just(catFact));
            slider.setProgress(200);
        }, 250);

        //assert
        verify(view, timeout(200)).updateSliderSelected(eq(100));
        verify(view, timeout(200)).displayErrorMessage(anyString());
        verify(view, timeout(400)).updateSliderSelected(eq(200));
        verify(view, timeout(400)).displayFact(eq("Test fact"));
    }

    @Test
    public void shareFactImageWithValidBitmapShouldCallShare() throws Exception {
        //Arrange
        Bitmap bitmap = Mockito.mock(Bitmap.class);
        Single<File> observable  = ObservableUtils.createSaveImageObservable(context, bitmap, "test_file");

        //Act
        presenter.shareFactImage(observable);

        //Assert
        verify(view).share(argThat(argument -> argument.getName().contains("test_file")));
        verify(view).showLoading();
        verify(view).hideLoading();
    }

    @Test
    public void shareFactImageWithNullBitmapShouldDisplayError() throws Exception {
        //Arrange
        Context context = RuntimeEnvironment.application.getApplicationContext();
        Single<File> observable  = ObservableUtils.createSaveImageObservable(context, null, "test_file");

        //Act
        presenter.shareFactImage(observable);

        //Assert
        verify(view).displayErrorMessage(anyString());
        verify(view).showLoading();
        verify(view).hideLoading();
    }

}