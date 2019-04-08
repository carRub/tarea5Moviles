package com.iteso.sesion9.tools;

public class Constants {
    public static final int TYPE_MAC = 1;
    public static final int TYPE_ALIENWARE = 2;
    public static final int TYPE_SHEETS = 3;
    public static final int TYPE_PILLOW = 4;
    public static final int TYPE_REFRIGERATOR = 5;
    public static final int TYPE_MICRO = 6;

    public static final String EXTRA_PRODUCT = "PRODUCT";
    public static final String EXTRA_FRAGMENT = "FRAGMENT";
    public static final int FRAGMENT_TECHNOLOGY = 0;
    public static final int FRAGMENT_HOME = 1;
    public static final int FRAGMENT_ELECTRONICS = 2;

    public static final int ACTIVITY_DETAIL = 9999;

    public static final String USER_PREFERENCES = "com.iteso.USER_PREFERENCES";
    public static final int SPLASH_SCREEN_DELAY = 2000;

    //Database name and version
    public static final String DATABASE_NAME = "MyProducts.db";
    public static final int DATABASE_VERSION = 1;

    //table names
    public static final String TABLE_CITY = "city";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_STORE = "store";
    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_STORE_PRODUCT = "storeProduct";

    // City table's columns
    public static final String KEY_CITY_ID = "id";
    public static final String KEY_CITY_NAME = "name";

    //Category table's columns
    public static final String KEY_CATEGORY_ID = "id";
    public static final String KEY_CATEGORY_NAME = "name";

    //Store table's columns
    public static final String KEY_STORE_ID = "store";
    public static final String KEY_STORE_NAME = "name";
    public static final String KEY_STORE_PHONE = "phone";
    public static final String KEY_STORE_ID_CITY = "idCity";
    public static final String KEY_STORE_THUMBNAIL = "thumbnail";
    public static final String KEY_STORE_LATITUDE = "latitude";
    public static final String KEY_STORE_LONGITUDE = "longitude";

    //Product table's columns
    public static final String KEY_PRODUCT_ID_PRODUCT = "idProduct";
    public static final String KEY_PRODUCT_TITLE = "title";
    public static final String KEY_PRODUCT_IMAGE = "image";
    public static final String KEY_PRODUCT_ID_CATEGORY = "idCategory";

    //StoreProduct table's columns
    public static final String KEY_STORE_PRODUCT_ID = "id";
    public static final String KEY_STORE_PRODUCT_ID_PRODUCT = "idProduct";
    public static final String KEY_STORE_PRODUCT_ID_STORE = "idStore";

}
