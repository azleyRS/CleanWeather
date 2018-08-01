package com.example.rus.cleanweather.data.repository;

import android.util.Log;

import com.example.rus.cleanweather.data.entity.Weather5;
import com.example.rus.cleanweather.data.entity.WeatherDayEntity;
import com.example.rus.cleanweather.data.mapper.WeatherDayMapper;
import com.example.rus.cleanweather.data.database.DBManager;
import com.example.rus.cleanweather.data.network.RetrofitHelper;
import com.example.rus.cleanweather.data.network.WeatherAPI;
import com.example.rus.cleanweather.domain.WeatherDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataProvider {
    @Inject
    DBManager manager;

    @Inject
    RetrofitHelper helper;


    public DataProvider(DBManager dbManager,RetrofitHelper retrofitHelper) {
        manager = dbManager;
        helper = retrofitHelper;
    }

    public List<WeatherDay> getWeatherDayList() {

        //for testing
/*        WeatherDayEntity testing = new WeatherDayEntity();
        testing.setTimestamp(1234512345);
        testing.setTemp(1.1,1.1,1.1,1.1,1.1,1.1,1,1.1);
        List<WeatherDayEntity> list = new ArrayList<>();
        list.add(testing);
        manager.addWeatherList(list);*/

        return WeatherDayMapper.transform(manager.getWeatherDaysList());
    }

    public Single<Weather5> loadWeather(){
        Single<Weather5> weather5Single = helper.getService().getForecastRx(WeatherAPI.CITY_ID, "metric",WeatherAPI.KEY);
        Log.v("tag", "RX");

        weather5Single.subscribeOn(Schedulers.single())
                .subscribe(new SingleObserver<Weather5>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Weather5 weather5) {
                        List<WeatherDayEntity> weatherDayListEntity = new ArrayList<>();
                        for (WeatherDayEntity weatherDay : weather5.getItems()){
                            if (weatherDay.getDateCalendar().get(Calendar.HOUR_OF_DAY) == 15){
                                weatherDayListEntity.add(weatherDay);
                                Log.v("TAG", "add in weatherDayList");
                            }
                        }
                        manager.addWeatherList(weatherDayListEntity);
                        Log.v("TAG", "Added in DB " + weatherDayListEntity.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


        return weather5Single;
/*        helper.getService().getForecast(WeatherAPI.CITY_ID, "metric",WeatherAPI.KEY).enqueue(new Callback<Weather5>() {
            @Override
            public void onResponse(Call<Weather5> call, Response<Weather5> response) {
                Log.v("TAG", "RECIEVED");
                Weather5 weather5 = response.body();
                List<WeatherDayEntity> weatherDayListEntity = new ArrayList<>();
                for (WeatherDayEntity weatherDay : weather5.getItems()){
                    if (weatherDay.getDateCalendar().get(Calendar.HOUR_OF_DAY) == 15){
                        weatherDayListEntity.add(weatherDay);
                        Log.v("TAG", "add in weatherDayList");
                    }
                }
                manager.addWeatherList(weatherDayListEntity);
                Log.v("TAG", "Added in DB " + weatherDayListEntity.size());
            }

            @Override
            public void onFailure(Call<Weather5> call, Throwable t) {

            }
        });*/
    }

    public WeatherDay getWeatherDay(int position) {
        return WeatherDayMapper.transform(manager.getWeatherDay(position));
    }
}
