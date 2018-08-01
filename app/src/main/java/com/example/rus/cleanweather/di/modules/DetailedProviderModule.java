package com.example.rus.cleanweather.di.modules;

import com.example.rus.cleanweather.domain.Interactor.GetWeatherDayListInteractor;
import com.example.rus.cleanweather.presentation.presenter.DetailedPresenter;

import dagger.Module;
import dagger.Provides;
@Module
public class DetailedProviderModule {
    @Provides
    public DetailedPresenter provideDetailedPresenter(GetWeatherDayListInteractor getWeatherDayListInteractor){
        return new DetailedPresenter(getWeatherDayListInteractor);
    }
}
