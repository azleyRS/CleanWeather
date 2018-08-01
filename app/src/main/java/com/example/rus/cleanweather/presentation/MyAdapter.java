package com.example.rus.cleanweather.presentation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rus.cleanweather.R;
import com.example.rus.cleanweather.presentation.model.WeatherDayModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<WeatherDayModel> list;

    public MyAdapter() {
        list = new ArrayList<>();
    }

    public void updateAdapter(List<WeatherDayModel> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindPosition(position);

        String date = String.format("%d.%d.%d %d",
                list.get(position).getDate().get(Calendar.DAY_OF_MONTH),
                list.get(position).getDate().get(Calendar.WEEK_OF_MONTH),
                list.get(position).getDate().get(Calendar.YEAR),
                list.get(position).getDate().get(Calendar.HOUR_OF_DAY));
        holder.mTime.setText(date);

        String result = "Avg temp in Moscow = " + list.get(position).getTemp() +
                "\n Min temp = " + list.get(position).getTemp_min() +
                "\n Min temp = " + list.get(position).getTemp_max();
        holder.mName.setText(result);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        int positionForBd;

        TextView mTime;
        TextView mName;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.temp);
            mTime = itemView.findViewById(R.id.day);
        }
        public void bindPosition(int position){
            positionForBd = position;
        }

    }
}
