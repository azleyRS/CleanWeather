package com.example.rus.cleanweather;

import android.app.Application;

import com.example.rus.cleanweather.di.component.AppComponent;
import com.example.rus.cleanweather.di.component.DaggerAppComponent;
import com.example.rus.cleanweather.di.modules.AdapterModule;
import com.example.rus.cleanweather.di.modules.ContextModule;

public class AppApplication extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
    }
}
