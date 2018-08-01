package com.example.rus.cleanweather.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private Retrofit retrofit = null;

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    public WeatherAPI.ApiInterface getService(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(WeatherAPI.ApiInterface.class);
    }
}
