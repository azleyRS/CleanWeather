package com.example.rus.cleanweather.presentation.presenter;


import android.util.Log;

import com.example.rus.cleanweather.data.entity.Weather5;
import com.example.rus.cleanweather.domain.Interactor.GetWeatherDayListInteractor;
import com.example.rus.cleanweather.presentation.mapper.WeatherDayModelDataMapper;
import com.example.rus.cleanweather.presentation.model.WeatherDayModel;
import com.example.rus.cleanweather.presentation.view.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class MainPresenter {
    private MainView mainView;
    private GetWeatherDayListInteractor getWeatherDayListInteractor;

    public MainPresenter(GetWeatherDayListInteractor getWeatherDayListInteractor) {
        this.getWeatherDayListInteractor = getWeatherDayListInteractor;
        Log.v("tag", "presenter created");
    }

    public void attachView(MainView mainActivity) {
        mainView = mainActivity;
    }

    public void updateAdapter() {
        List<WeatherDayModel> weatherDayModelList = WeatherDayModelDataMapper.transform(getWeatherDayListInteractor.getWeatherDayList());
        mainView.updateAdapter(weatherDayModelList);
    }

    public Single<Weather5> loadWeather() {
        return getWeatherDayListInteractor.loadWeather();
    }
}
