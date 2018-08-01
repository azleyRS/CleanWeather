package com.example.rus.cleanweather.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class WeatherDayEntity {

    @SerializedName("main")
    private WeatherTemp temp;

    @SerializedName("dt")
    private long timestamp;

    public class WeatherTemp {
        Double temp;
        Double temp_min;
        Double temp_max;
        Double pressure;
        Double sea_level;
        Double grnd_level;
        Integer humidity;
        Double temp_kf;

        public WeatherTemp(Double temp, Double temp_min, Double temp_max, Double pressure, Double sea_level, Double grnd_level, Integer humidity, Double temp_kf) {
            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.sea_level = sea_level;
            this.grnd_level = grnd_level;
            this.humidity = humidity;
            this.temp_kf = temp_kf;
        }
    }

    public void setTemp( Double temp, Double temp_min,
            Double temp_max,
            Double pressure,
            Double sea_level,
            Double grnd_level,
            Integer humidity,
            Double temp_kf) {
        this.temp = new WeatherTemp(temp, temp_min, temp_max, pressure, sea_level, grnd_level, humidity, temp_kf);
    }

    public Calendar getDate() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp * 1000);
        return date;
    }

    public Calendar getDateCalendar() {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(timestamp * 1000);
        return date;
    }

    public String getTemp() { return String.valueOf(temp.temp); }

    public String getTempMin() { return String.valueOf(temp.temp_min); }

    public String getTempMax() { return String.valueOf(temp.temp_max); }


    public Double getPressure() {
        return temp.pressure;
    }

    public Double getSea_level() {
        return temp.sea_level;
    }

    public Double getGrnd_level() {
        return temp.grnd_level;
    }

    public Integer getHumidity() {
        return temp.humidity;
    }

    public Double getTemp_kf() {
        return temp.temp_kf;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
