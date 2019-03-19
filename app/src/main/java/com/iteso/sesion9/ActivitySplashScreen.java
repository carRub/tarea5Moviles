package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.tools.Constant;

import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    public User loadPreferences(){

        SharedPreferences sharedPreferences = getSharedPreferences(Constant.USER_PREFERENCES, MODE_PRIVATE);
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
        timer.schedule(task, Constant.SPLASH_SCREEN_DELAY);

    }
}
