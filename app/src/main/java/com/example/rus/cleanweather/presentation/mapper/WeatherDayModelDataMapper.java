package com.example.rus.cleanweather.presentation.mapper;

import com.example.rus.cleanweather.domain.WeatherDay;
import com.example.rus.cleanweather.presentation.model.WeatherDayModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherDayModelDataMapper {
    public static WeatherDayModel transform(WeatherDay weatherDay){
        WeatherDayModel weatherDayModel = new WeatherDayModel();
        weatherDayModel.setTimestamp(weatherDay.getTimestamp());
        weatherDayModel.setGrnd_level(String.valueOf(weatherDay.getGrnd_level()));
        weatherDayModel.setTemp(String.valueOf(weatherDay.getTemp()));
        weatherDayModel.setTemp_max(String.valueOf(weatherDay.getTemp_max()));
        weatherDayModel.setTemp_min(String.valueOf(weatherDay.getTemp_min()));
        weatherDayModel.setPressure(String.valueOf(weatherDay.getPressure()));
        weatherDayModel.setSea_level(String.valueOf(weatherDay.getSea_level()));
        weatherDayModel.setHumidity(String.valueOf(weatherDay.getHumidity()));
        weatherDayModel.setTemp_kf(String.valueOf(weatherDay.getTemp_kf()));
        return weatherDayModel;
    }

    public static List<WeatherDayModel> transform(List<WeatherDay> weatherDayCollection){
        List<WeatherDayModel> weatherDayModelCollection = new ArrayList<>();
        for (WeatherDay weatherDay : weatherDayCollection){
            weatherDayModelCollection.add(transform(weatherDay));
        }
        return weatherDayModelCollection;
    }
}