package com.example.rus.cleanweather.di.modules;

import com.example.rus.cleanweather.presentation.MyAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {
    @Provides
    public MyAdapter provideMyAdapter(){
        return new MyAdapter();
    }
}
