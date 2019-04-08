package com.iteso.sesion9.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.tools.Constants;

public class StoreProductControl {

    StoreControl storeControl = new StoreControl();
    //ItemProductControl itemProductControl = new ItemProductControl();

    public Store getStoreByProductID(int id, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        Store store;
        int storeId = 0;

        String select = "SELECT " + "'" + Constants.KEY_STORE_PRODUCT_ID_PRODUCT + "'" + " FROM " + Constants.TABLE_STORE_PRODUCT
                + " WHERE " + Constants.KEY_STORE_PRODUCT_ID_PRODUCT + " = " + id;

        Cursor cursor = db.rawQuery(select, null);

        while (cursor.moveToNext()){
            storeId = cursor.getInt(0);
        }

        store = storeControl.getStoreById(storeId, dh);
        return store;
    }
}
