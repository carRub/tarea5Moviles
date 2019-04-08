package com.iteso.sesion9.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.tools.Constants;

import java.util.ArrayList;

public class CategoryControl {

    public ArrayList<Category> getCategories(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Category> categories = new ArrayList<>();

        String select = "SELECT " + Constants.KEY_CATEGORY_ID + ", " + Constants.KEY_CATEGORY_NAME + " FROM " + Constants.TABLE_CATEGORY;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            categories.add(category);
        }
        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        return categories;

    }

    public Category getCategoryByName(String name, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        Category category = new Category();

        String select = "SELECT " + Constants.KEY_CATEGORY_ID + ", " + Constants.KEY_CATEGORY_NAME + " FROM " + Constants.TABLE_CATEGORY + " WHERE " + Constants.KEY_CATEGORY_NAME + " = " + "'" + name + "'";
        Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext()){
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
        }
        return category;
    }



}
