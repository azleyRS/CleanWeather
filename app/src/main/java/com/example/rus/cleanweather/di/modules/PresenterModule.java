package com.example.rus.cleanweather.di.modules;

import com.example.rus.cleanweather.domain.Interactor.GetWeatherDayListInteractor;
import com.example.rus.cleanweather.presentation.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;
@Module
public class PresenterModule {
    @Provides
    public MainPresenter provideMainPresenter(GetWeatherDayListInteractor getWeatherDayListInteractor){
        return new MainPresenter(getWeatherDayListInteractor);
    }
}
