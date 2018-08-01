package com.example.rus.cleanweather.di.modules;

import android.content.Context;
import android.util.Log;

import com.example.rus.cleanweather.data.database.DBManager;
import com.example.rus.cleanweather.data.network.RetrofitHelper;
import com.example.rus.cleanweather.data.repository.DataProvider;
import com.example.rus.cleanweather.di.scopes.DataProviderScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class DataProviderModule {
    @DataProviderScope
    @Provides
    public DataProvider provideDataProvider(DBManager manager, RetrofitHelper retrofitHelper){
        Log.v("tag", "DataProvider created");
        return new DataProvider(manager,retrofitHelper);
    }
    @DataProviderScope
    @Provides
    public DBManager provideManager(Context context){
        Log.v("tag", "DBManager created");
        return new DBManager(context);
    }
    @DataProviderScope
    @Provides
    public RetrofitHelper provideRetrofitHelper(){
        Log.v("tag", "RetrofitHelper created");

        return new RetrofitHelper();
    }
}
