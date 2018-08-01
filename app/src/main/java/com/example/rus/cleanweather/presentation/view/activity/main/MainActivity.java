package com.example.rus.cleanweather.presentation.view.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rus.cleanweather.AppApplication;
import com.example.rus.cleanweather.R;
import com.example.rus.cleanweather.data.entity.Weather5;
import com.example.rus.cleanweather.data.mapper.WeatherDayMapper;
import com.example.rus.cleanweather.presentation.MyAdapter;
import com.example.rus.cleanweather.presentation.mapper.WeatherDayModelDataMapper;
import com.example.rus.cleanweather.presentation.model.WeatherDayModel;
import com.example.rus.cleanweather.presentation.presenter.MainPresenter;
import com.example.rus.cleanweather.presentation.view.MainView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainView {

    RecyclerView recyclerView;

    @Inject
    MyAdapter adapter;
    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppApplication.getComponent().inject(this);
        recyclerView.setAdapter(adapter);

        mainPresenter.attachView(this);
        mainPresenter.updateAdapter();


    }

    @Override
    public void updateAdapter(List<WeatherDayModel> weatherDayModelList) {
        adapter.updateAdapter(weatherDayModelList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_menu_button:
                Toast.makeText(this,"test",Toast.LENGTH_LONG).show();

                mainPresenter.loadWeather().subscribeOn(Schedulers.single())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<Weather5>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Weather5 weather5) {
                                List<WeatherDayModel> weatherDayModels = new ArrayList<>();
                                for (WeatherDayModel weatherDay : WeatherDayModelDataMapper.transform(WeatherDayMapper.transform(weather5.getItems()))){
                                    if (weatherDay.getDate().get(Calendar.HOUR_OF_DAY) == 15){
                                        weatherDayModels.add(weatherDay);
                                        Log.v("TAG", "add in weatherDayList");
                                    }
                                }


                                //adapter.updateAdapter(WeatherDayModelDataMapper.transform(WeatherDayMapper.transform(weather5.getItems())));
                                adapter.updateAdapter(weatherDayModels);
                                Log.v("TAG", "here");
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
        }
        return super.onOptionsItemSelected(item);
    }
}
