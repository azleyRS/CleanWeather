package com.example.rus.cleanweather.di.component;

import com.example.rus.cleanweather.di.modules.ContextModule;
import com.example.rus.cleanweather.di.modules.DataProviderModule;
import com.example.rus.cleanweather.di.modules.InteractorModule;
import com.example.rus.cleanweather.di.modules.PresenterModule;
import com.example.rus.cleanweather.di.scopes.DataProviderScope;
import com.example.rus.cleanweather.presentation.view.activity.main.MainActivity;
import com.example.rus.cleanweather.di.modules.AdapterModule;

import dagger.Component;
@DataProviderScope
@Component(modules = {AdapterModule.class, PresenterModule.class, InteractorModule.class, DataProviderModule.class,ContextModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
