package com.example.rus.cleanweather.data.mapper;

import com.example.rus.cleanweather.data.entity.WeatherDayEntity;
import com.example.rus.cleanweather.domain.WeatherDay;

import java.util.ArrayList;
import java.util.List;

public class WeatherDayMapper {
    public static WeatherDay transform(WeatherDayEntity weatherDay){
        WeatherDay weatherDayDomain = new WeatherDay();
        weatherDayDomain.setTimestamp(weatherDay.getTimestamp());
        weatherDayDomain.setGrnd_level(weatherDay.getGrnd_level());
        weatherDayDomain.setTemp(Double.valueOf(weatherDay.getTemp()));
        weatherDayDomain.setTemp_max(Double.valueOf(weatherDay.getTempMax()));
        weatherDayDomain.setTemp_min(Double.valueOf(weatherDay.getTempMin()));
        weatherDayDomain.setPressure(weatherDay.getPressure());
        weatherDayDomain.setSea_level(weatherDay.getSea_level());
        weatherDayDomain.setHumidity(weatherDay.getHumidity());
        weatherDayDomain.setTemp_kf(weatherDay.getTemp_kf());
        return weatherDayDomain;
    }

    public static List<WeatherDay> transform(List<WeatherDayEntity> weatherDayCollection){
        List<WeatherDay> weatherDayDomainCollection = new ArrayList<>();
        for (WeatherDayEntity weatherDay : weatherDayCollection){
            weatherDayDomainCollection.add(transform(weatherDay));
        }
        return weatherDayDomainCollection;
    }
}
