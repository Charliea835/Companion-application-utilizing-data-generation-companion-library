package com.compApp.ansel.companionapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/*

CHARLIE ANSELL
07/04/2019

 */
/*
This java file allows the splash screen to stay on the screen for a set time
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*makes the window full screen*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int SPLASH_DISPLAY_LENGTH = 500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*Create an intent that will start the main activity. */
                Intent mainIntent = new Intent(SplashActivity.this,UserLogin.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);//declare the length the window stays open for
    }
}