package com.example.rus.cleanweather.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather5 {
    @SerializedName("list")
    @Expose
    private List<WeatherDayEntity> items;

    public Weather5(List<WeatherDayEntity> items) {
        this.items = items;
    }

    public List<WeatherDayEntity> getItems() {
        return items;
    }
}
