package com.example.rus.cleanweather.di.modules;

import android.util.Log;

import com.example.rus.cleanweather.data.repository.DataProvider;
import com.example.rus.cleanweather.domain.Interactor.GetWeatherDayListInteractor;

import dagger.Module;
import dagger.Provides;
@Module
public class InteractorModule {
    @Provides
    public GetWeatherDayListInteractor provideGetWeatherDayListInteractor(DataProvider dataProvider){
        Log.v("tag", "Interactor created");
        return new GetWeatherDayListInteractor(dataProvider);
    }
}
