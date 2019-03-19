package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.tools.Constant;

public class ActivityLogin extends AppCompatActivity {

    EditText username, password;
    Button login;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.activity_login_username);
        password = findViewById(R.id.activity_login_password);
        login = findViewById(R.id.activity_login_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePreferences();

                Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void savePreferences(){

        User user = new User();

        user.setName(username.getText().toString().trim());
        user.setPassword(password.getText().toString().trim());
        user.setLogged(true);

        SharedPreferences sharedPreferences = getSharedPreferences(Constant.USER_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME", user.getName());
        editor.putString("PASSWORD", user.getPassword());
        editor.putBoolean("ISLOGGED", user.isLogged());
        editor.apply();

    }

}
