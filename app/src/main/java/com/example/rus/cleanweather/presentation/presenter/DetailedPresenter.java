package com.example.rus.cleanweather.presentation.presenter;

import android.util.Log;

import com.example.rus.cleanweather.domain.Interactor.GetWeatherDayListInteractor;
import com.example.rus.cleanweather.presentation.mapper.WeatherDayModelDataMapper;
import com.example.rus.cleanweather.presentation.model.WeatherDayModel;
import com.example.rus.cleanweather.presentation.view.DetailedView;


public class DetailedPresenter {

    private GetWeatherDayListInteractor getWeatherDayListInteractor;
    private DetailedView detailedView;

    public DetailedPresenter(GetWeatherDayListInteractor getWeatherDayListInteractor) {
        this.getWeatherDayListInteractor = getWeatherDayListInteractor;
        Log.v("tag", "detailedPresenter created");

    }

    public void attachView(DetailedView detailedView) {
        this.detailedView = detailedView;
    }

    public void getInfo(int position) {
        WeatherDayModel weatherDayModel = WeatherDayModelDataMapper.transform( getWeatherDayListInteractor.getWeatherDay(position));
        detailedView.setDetailedViews(weatherDayModel);
    }



    /*
    private DetailedModel model;
    private DetailedView detailedView;

    public DetailedPresenter(Model model) {
        this.model = model;
    }

    public void attachView(DetailedView view){
        detailedView = view;
    }

    @Override
    public void getInfo(int position) {
        Log.v("TAG", " Position recieved");
        WeatherDay weatherDay = model.getWeatherDay(position);
        detailedView.setDetailedViews(weatherDay);
    }*/
}
