package com.example.rus.cleanweather.di.modules;

import android.content.Context;

import com.example.rus.cleanweather.di.scopes.DataProviderScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class ContextModule {
    Context context;
    public ContextModule(Context context){
        this.context = context;
    }

    @DataProviderScope
    @Provides
    public Context context(){ return context; }
}
