package com.example.rus.cleanweather.data.network;

import com.example.rus.cleanweather.data.entity.Weather5;
import com.example.rus.cleanweather.data.entity.WeatherDayEntity;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherAPI {
    public static String KEY = "b3bdb37b1f087eb7b3b1a674bdcbb859";
    public static String CITY_ID = "524901";
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public interface ApiInterface {
        @GET("weather")
        Call<WeatherDayEntity> getToday(
                @Query("id") String id,
                @Query("units") String units,
                @Query("appid") String appid
        );

        @GET("forecast")
        Call<Weather5> getForecast(
                @Query("id") String id,
                @Query("units") String units,
                @Query("appid") String appid
        );

        @GET("forecast")
        Single<Weather5> getForecastRx(
                @Query("id") String id,
                @Query("units") String units,
                @Query("appid") String appid
        );

    }
}
