package com.example.igti.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        if(sp.getBoolean("showSplashScreen",true)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToMain();
                    editor.putBoolean("showSplashScreen", false);
                    editor.commit();
                }
            }, SPLASH_TIME_OUT);
        }else{
            goToMain();
            //editor.clear();
            //editor.commit();
        }
    }

    protected void goToMain(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
