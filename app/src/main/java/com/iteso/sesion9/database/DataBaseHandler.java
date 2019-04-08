package com.iteso.sesion9.database;

import android.arch.core.executor.DefaultTaskExecutor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.iteso.sesion9.tools.Constants.*;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static DataBaseHandler dataBaseHandler;

    public DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null)
            //Initializing
            dataBaseHandler = new DataBaseHandler(context);
        return  dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating city table
        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY +
                "(" + KEY_CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_CITY_NAME + " TEXT)";
        db.execSQL(CREATE_CITY_TABLE);

        //inserting cities
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_NAME + ") VALUES ('Zapopan')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_NAME + ") VALUES ('Guadalajara')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_NAME + ") VALUES ('Tlaquepaque')");

        //creating Category table
        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY +
                "(" + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY, "
                + KEY_CATEGORY_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORY_TABLE);

        //Inserting 3 categories
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_ID + ", " + KEY_CATEGORY_NAME + ") VALUES (0, 'TECHNOLOGY')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_ID + ", " + KEY_CATEGORY_NAME + ") VALUES (1, 'HOME')");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_ID + ", " + KEY_CATEGORY_NAME + ") VALUES (2, 'ELECTRONICS')");


        //creating store table
        String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE +
                "(" + KEY_STORE_ID + " INTEGER PRIMARY KEY, "
                + KEY_STORE_NAME + " TEXT, "
                + KEY_STORE_PHONE + " TEXT, "
                + KEY_STORE_ID_CITY + " INTEGER, "
                + KEY_STORE_THUMBNAIL + " INTEGER, "
                + KEY_STORE_LATITUDE + " DOUBLE, "
                + KEY_STORE_LONGITUDE + " DOUBLE)";
        db.execSQL(CREATE_STORE_TABLE);

        //creating product table
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT +
                "(" + KEY_PRODUCT_ID_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_PRODUCT_TITLE + " TEXT, "
                + KEY_PRODUCT_IMAGE + " INTEGER, "
                + KEY_PRODUCT_ID_CATEGORY + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);

        //creating StoreProduct table
        String CREATE_STORE_PRODUCT = "CREATE TABLE " + TABLE_STORE_PRODUCT +
                "(" + KEY_STORE_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_STORE_PRODUCT_ID_PRODUCT + " INTEGER, "
                + KEY_STORE_PRODUCT_ID_STORE + " INTEGER)";
        db.execSQL(CREATE_STORE_PRODUCT);

        //creating storeproduct table
        /*String CREATE_STORE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_STORE_PRODUCT +
                "(" + KEY_STORE_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_STORE_PRODUCT_ID_STORE + " INTEGER, "
                + KEY_STORE_PRODUCT_ID_PRODUCT + " INTEGER)";
        db.execSQL(CREATE_STORE_PRODUCT_TABLE);*/


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //this is the first Version of the DB
    }
}
