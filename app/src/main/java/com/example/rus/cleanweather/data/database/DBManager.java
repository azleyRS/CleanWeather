package com.example.rus.cleanweather.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rus.cleanweather.data.entity.WeatherDayEntity;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private WeatherBaseHelper weatherBaseHelper;
    public DBManager(Context context){
        this.weatherBaseHelper = new WeatherBaseHelper(context);
    }

    public void addWeatherList(List<WeatherDayEntity> weatherDayEntities){
        SQLiteDatabase database = null;
        try {
            database = weatherBaseHelper.getWritableDatabase();
            ContentValues contentValues = null;
            database.beginTransaction();
            database.delete(WeatherDbSchema.WeatherTable.NAME,null,null);
            for (int i = 0; i< weatherDayEntities.size(); i++){
                contentValues = getContentValues(weatherDayEntities.get(i), i+1);
                database.replace(WeatherDbSchema.WeatherTable.NAME, null, contentValues);
            }
            database.setTransactionSuccessful();
        } catch (SQLException e){
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (database !=null){
                if (database.inTransaction()){
                    database.endTransaction();
                }
                database.close();
            }
        }
    }

    public List<WeatherDayEntity> getWeatherDaysList(){
        List<WeatherDayEntity> weatherDayEntityList = new ArrayList<>();
        SQLiteDatabase database = null;
        try {
            database = weatherBaseHelper.getReadableDatabase();
            database.beginTransaction();
            Cursor cursor = database.query(WeatherDbSchema.WeatherTable.NAME,null,null,null,null,null,null);
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    WeatherDayEntity weatherDayEntity = new WeatherDayEntity();
                    Double temp = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP)));
                    Double temp_min = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MIN)));
                    Double temp_max = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MAX)));
                    Double pressure = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.PRESSURE));
                    Double sea_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.SEA_LEVEL));
                    Double grnd_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.GRND_LEVEL));
                    Integer humidity = cursor.getInt(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.HUMIDITY));
                    Double temp_kf = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_KF));
                    weatherDayEntity.setTemp(temp,temp_min,temp_max,pressure,sea_level,grnd_level,humidity,temp_kf);
                    weatherDayEntity.setTimestamp(cursor.getLong(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.DT)));
                    weatherDayEntityList.add(weatherDayEntity);
                    cursor.moveToNext();
                }
            } finally {
                cursor.close();
            }
            database.setTransactionSuccessful();
        } catch (SQLException e){
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (database !=null){
                if (database.inTransaction()){
                    database.endTransaction();
                }
                database.close();
            }
        }
        return weatherDayEntityList;
    }


    private ContentValues getContentValues(WeatherDayEntity weatherDayEntity, int i) {
        ContentValues values = new ContentValues();
        values.put("_id", i);
        values.put(WeatherDbSchema.WeatherTable.Cols.DT, weatherDayEntity.getTimestamp());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP, weatherDayEntity.getTemp());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP_MIN, weatherDayEntity.getTempMin());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP_MAX, weatherDayEntity.getTempMax());
        values.put(WeatherDbSchema.WeatherTable.Cols.PRESSURE, weatherDayEntity.getPressure());
        values.put(WeatherDbSchema.WeatherTable.Cols.SEA_LEVEL, weatherDayEntity.getSea_level());
        values.put(WeatherDbSchema.WeatherTable.Cols.GRND_LEVEL, weatherDayEntity.getGrnd_level());
        values.put(WeatherDbSchema.WeatherTable.Cols.HUMIDITY, weatherDayEntity.getHumidity());
        values.put(WeatherDbSchema.WeatherTable.Cols.TEMP_KF, weatherDayEntity.getTemp_kf());
        return values;
    }

    public WeatherDayEntity getWeatherDay(int position) {
        WeatherDayEntity weatherDayEntity = new WeatherDayEntity();
        SQLiteDatabase database = null;
        try{
            database = weatherBaseHelper.getReadableDatabase();
            database.beginTransaction();
            String whereClause = "_id = ?";
            //SQlite stars with 1, arraylist with 0
            String[] whereArgs = new String[]{String.valueOf(position + 1)};
            Cursor cursor = database.query(WeatherDbSchema.WeatherTable.NAME,null,whereClause,whereArgs,null,null,null);
            if (cursor.moveToFirst()){
                Double temp = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP)));
                Double temp_min = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MIN)));
                Double temp_max = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_MAX)));
                Double pressure = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.PRESSURE));
                Double sea_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.SEA_LEVEL));
                Double grnd_level = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.GRND_LEVEL));
                Integer humidity = cursor.getInt(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.HUMIDITY));
                Double temp_kf = cursor.getDouble(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.TEMP_KF));
                weatherDayEntity.setTemp(temp,temp_min,temp_max,pressure,sea_level,grnd_level,humidity,temp_kf);
                weatherDayEntity.setTimestamp(cursor.getLong(cursor.getColumnIndex(WeatherDbSchema.WeatherTable.Cols.DT)));
            }
            cursor.close();
            database.setTransactionSuccessful();
        } catch (SQLException e){
            Log.v("SQLiteExeption", e.getMessage());
        } finally {
            if (database !=null){
                if (database.inTransaction()){
                    database.endTransaction();
                }
                database.close();
            }
        }
        return weatherDayEntity;
    }
}
