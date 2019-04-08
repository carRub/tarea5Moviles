package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.database.CityControl;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.StoreControl;
import com.iteso.sesion9.tools.Constants;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    public User loadPreferences(){

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        User user = new User();

        user.setName(sharedPreferences.getString("USERNAME", null));
        user.setPassword(sharedPreferences.getString("PASSWORD", null));
        user.setLogged(sharedPreferences.getBoolean("ISLOGGED", false));

        sharedPreferences = null;
        return user;
    }


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        StoreControl storeControl = new StoreControl();
        DataBaseHandler db = DataBaseHandler.getInstance(this);
        ArrayList<Store> stores = storeControl.getStores(db);

        if(stores.size() == 0){
            CityControl cityControl = new CityControl();
            ArrayList<City> cities = cityControl.getCities(db);

            storeControl.addStore(new Store(0, "BestBuy Ciudadela", "3314012302", R.drawable.bestbuy, 21.555, -108.343, cities.get(0)), db);
            storeControl.addStore(new Store(2, "BestBuy Galerias", "3313015502", R.drawable.bestbuy, 123.565, 53.643, cities.get(1)), db);
            storeControl.addStore(new Store(1, "BestBuy Tlaquepaque", "3314015502", R.drawable.bestbuy, 87.235, 103.543, cities.get(2)), db);
        }


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                User user = loadPreferences();
                Intent intent;
                if(user.isLogged())
                    intent = new Intent().setClass(ActivitySplashScreen.this, MainActivity.class);
                else
                    intent = new Intent().setClass(ActivitySplashScreen.this, ActivityLogin.class);
                startActivity(intent);
                finish();

            }
        };
        Timer timer = new Timer();
        timer.schedule(task, Constants.SPLASH_SCREEN_DELAY);

    }
}
