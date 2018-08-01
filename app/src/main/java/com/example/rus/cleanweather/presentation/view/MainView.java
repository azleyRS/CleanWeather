package com.example.rus.cleanweather.presentation.view;

import com.example.rus.cleanweather.presentation.model.WeatherDayModel;

import java.util.List;

public interface MainView {
    void updateAdapter(List<WeatherDayModel> weatherDayModelList);
}
