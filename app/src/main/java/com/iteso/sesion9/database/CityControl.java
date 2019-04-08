package com.iteso.sesion9.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.City;

import java.util.ArrayList;

public class CityControl {

    public ArrayList<City> getCities(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<City> cities = new ArrayList<>();

        String select = "SELECT id, name FROM category";
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            City city = new City();
            city.setId(cursor.getInt(0));
            city.setName(cursor.getString(1));
            cities.add(city);
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        return cities;

    }
}
