package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.tools.Constants;

import java.util.ArrayList;

public class StoreControl {

    public void addStore(Store store, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.KEY_STORE_ID, store.getId());
        contentValues.put(Constants.KEY_STORE_NAME, store.getName());
        contentValues.put(Constants.KEY_STORE_PHONE, store.getPhone());
        contentValues.put(Constants.KEY_STORE_THUMBNAIL, store.getThumbnail());
        contentValues.put(Constants.KEY_STORE_LATITUDE, store.getLatitude());
        contentValues.put(Constants.KEY_STORE_LONGITUDE, store.getLongitude());
        contentValues.put(Constants.KEY_STORE_ID_CITY, store.getCity().getId());

        long rowID = db.insert(Constants.TABLE_STORE, null, contentValues);

        try{
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<Store> getStores(DataBaseHandler dh){

        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Store> stores = new ArrayList<>();

        /*String values = Constants.KEY_STORE_ID + ", " + Constants.KEY_STORE_NAME + ", " + Constants.KEY_STORE_PHONE + ", "
                + Constants.KEY_STORE_THUMBNAIL + ", " + Constants.KEY_STORE_LATITUDE + ", " + Constants.KEY_STORE_LONGITUDE + ", " +
                Constants.KEY_STORE_ID_CITY;*/

        String select = "SELECT " + Constants.KEY_STORE_ID + ", " + Constants.KEY_STORE_NAME + ", " + Constants.KEY_STORE_PHONE + ", "
                + Constants.KEY_STORE_THUMBNAIL + ", " + Constants.KEY_STORE_LATITUDE + ", " + Constants.KEY_STORE_LONGITUDE + ", "
                + Constants.KEY_STORE_ID_CITY + " FROM " + Constants.TABLE_STORE;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));
            City city = new City();
            city.setId(cursor.getInt(6));
            //city.setName(cursor.getString(7));
            store.setCity(city);
            stores.add(store);
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        return stores;

    }

    public Store getStoreById(int idStore, DataBaseHandler dh){

        SQLiteDatabase db = dh.getReadableDatabase();
        Store store = new Store();

        String select = "SELECT S." + Constants.KEY_STORE_ID + ", S." + Constants.KEY_STORE_NAME +
                ", S." + Constants.KEY_STORE_PHONE + ", S." + Constants.KEY_STORE_THUMBNAIL + ", S."
                + Constants.KEY_STORE_LATITUDE + ", S." + Constants.KEY_STORE_LONGITUDE + ", S." +
                Constants.KEY_STORE_ID_CITY + ", C." + Constants.KEY_CITY_NAME + " FROM " +
                Constants.TABLE_STORE + " S, " + Constants.TABLE_CITY + " C WHERE S." + Constants.KEY_STORE_ID +
                "= " + idStore +  " AND S." + Constants.KEY_STORE_ID_CITY
                + " = C." + Constants.KEY_CITY_ID;

        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()) {
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setThumbnail(cursor.getInt(3));
            store.setLatitude(cursor.getDouble(4));
            store.setLongitude(cursor.getDouble(5));
            City city = new City();
            city.setId(cursor.getInt(6));
            city.setName(cursor.getString(7));
            store.setCity(city);
        }

        try{
            cursor.close();
            db.close();
        }catch(Exception e){

        }

        return store;

    }

    public int getStoreIdByName(String name, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        int id = 0;

        String select = "SELECT " + Constants.KEY_STORE_ID + " FROM " + Constants.TABLE_STORE + " WHERE " + Constants.KEY_STORE_NAME + " = " + "'" + name + "'";
        Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
        }
        return id;
    }


}
