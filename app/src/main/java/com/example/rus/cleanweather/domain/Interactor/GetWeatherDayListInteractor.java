package com.example.rus.cleanweather.domain.Interactor;

import com.example.rus.cleanweather.data.entity.Weather5;
import com.example.rus.cleanweather.data.repository.DataProvider;
import com.example.rus.cleanweather.domain.WeatherDay;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class GetWeatherDayListInteractor {
    private DataProvider dataProvider;
    public GetWeatherDayListInteractor(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("11111");
        list.add("22222");
        list.add("33333");
        list.add("44444");
        return list;
    }

    public List<WeatherDay> getWeatherDayList() {
        return dataProvider.getWeatherDayList();
    }

    public Single<Weather5> loadWeather() {
        return dataProvider.loadWeather();
    }

    public WeatherDay getWeatherDay(int position) {
        return dataProvider.getWeatherDay(position);
    }
}
