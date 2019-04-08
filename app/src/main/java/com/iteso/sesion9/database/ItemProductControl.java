package com.iteso.sesion9.database;

import android.content.ClipData;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.tools.Constants;

import java.util.ArrayList;

public class ItemProductControl {

    StoreProductControl storeProductControl = new StoreProductControl();

    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh){

        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(Constants.KEY_PRODUCT_ID_PRODUCT, itemProduct.getCode());
        contentValues.put(Constants.KEY_PRODUCT_TITLE, itemProduct.getTitle());
        contentValues.put(Constants.KEY_PRODUCT_IMAGE, itemProduct.getImage());
        contentValues.put(Constants.KEY_PRODUCT_ID_CATEGORY, itemProduct.getCategory().getId());

        long rowID = db.insert(Constants.TABLE_PRODUCT, null, contentValues);

        ContentValues contentValues1 = new ContentValues();

        contentValues1.put(Constants.KEY_STORE_PRODUCT_ID_PRODUCT, itemProduct.getCode());
        contentValues1.put(Constants.KEY_STORE_PRODUCT_ID_STORE, itemProduct.getStore().getId());

        long rowID2 = db.insert(Constants.TABLE_STORE_PRODUCT, null, contentValues1);

    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){

        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<ItemProduct> itemProducts = new ArrayList<>();

        String select = "SELECT P." + Constants.KEY_PRODUCT_ID_PRODUCT + ", P." + Constants.KEY_PRODUCT_TITLE + ", P." + Constants.KEY_PRODUCT_IMAGE + ", P."
                + Constants.KEY_PRODUCT_ID_CATEGORY + ", C." + Constants.KEY_CATEGORY_ID + ", C." + Constants.KEY_CATEGORY_NAME +  " FROM " + Constants.TABLE_PRODUCT
                + " P, " + Constants.TABLE_CATEGORY + " C WHERE P."
                + Constants.KEY_PRODUCT_ID_CATEGORY + " = " + idCategory;

        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()){
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            Category category = new Category();
            category.setId(cursor.getInt(3));
            category.setName(cursor.getString(4));
            itemProduct.setCategory(category);
            Store store = new Store();
            store = storeProductControl.getStoreByProductID(cursor.getInt(0), dh);
            itemProducts.add(itemProduct);
        }

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        /*Store store;
        for(int i = 0; i < itemProducts.size(); i ++) {
            int code = itemProducts.get(i).getCode();
            store = storeProductControl.getStoreByProductID(code, dh);
            itemProducts.get(i).setStore(store);
        }*/

        return itemProducts;
    }
}
