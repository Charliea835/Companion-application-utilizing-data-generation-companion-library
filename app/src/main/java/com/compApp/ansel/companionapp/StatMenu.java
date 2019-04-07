package com.compApp.ansel.companionapp;


/*
CHARLIE ANSELL
07/04/2019

This script shows the statMenu which lets users decide if they are going to view there stats or view the leaderboard.
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class StatMenu extends AppCompatActivity {
    String username;
    String password;
    String game;

    //when the scene first loads.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statmenu);
        TextView info = findViewById(R.id.infoText);
        info.setText("Choose what you want to view");
        Bundle extras = getIntent().getExtras();
        username = extras.getString("KEY_USERNAME");
        password = extras.getString("KEY_PASSWORD");
        game = extras.getString("KEY_GAME");
        String[] items = {getResources().getString(R.string.showStats), getResources().getString(R.string.showLeaderboard)};
        Log.d("username", username);
        Log.d("password", password);

    }

               //show the user their stats by sending the user to the showStats class
               public void showStats(View v) {
                   Intent intent = new Intent(StatMenu.this, ShowStats.class);
                   Bundle extras = new Bundle();
                   extras.putString("KEY_GAME", game);
                   extras.putString("KEY_USERNAME", username); //send values to the showStats screen
                   extras.putString("KEY_PASSWORD", password);
                   Log.d("username2", username);
                   Log.d("password2", password);
                   intent.putExtras(extras);
                   startActivity(intent);//go to the showStats class
               }


               //take the user to the showLeadserboard class if they click on the button.
               public void showLeaderboard(View v){
                    Intent intent = new Intent(StatMenu.this,ShowLeaderboard.class);
                    Bundle extras = new Bundle();
                    extras.putString("KEY_GAME",game);
                    extras.putString("KEY_USERNAME",username);
                    extras.putString("KEY_PASSWORD",password);
                    Log.d("username2",username);
                    Log.d("password2",password);
                    intent.putExtras(extras);
                    startActivity(intent);
                }

    }



