package com.compApp.ansel.companionapp;

/*
Charlie Ansell
07/04/2019

This script shows the menu to the user where they can choose what game they are going to pick.

 */


// Import libraries
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class Main_menu extends AppCompatActivity {
    String username;
    String password;

    //The onCreate will set the contentView and get the required UI layout.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        TextView info = findViewById(R.id.infoText);
        info.setText("Choose game to view stats");
        Bundle extras = getIntent().getExtras();
        username = extras.getString("KEY_USERNAME"); //get the username sent from login screen
        password = extras.getString("KEY_PASSWORD"); //get the password sent from login screen
        String[] items = {getResources().getString(R.string.ThirdPersonShooter), getResources().getString(R.string.Platformer)};
        Log.d("username", username);
        Log.d("password", password);
    }


        //When button pressed go to the Stat menu
        public void TPS(View v) {

                Intent intent = new Intent(Main_menu.this, StatMenu.class); //intent to new screen statMenu
                Bundle extras = new Bundle(); //new bundle to send variables
                extras.putString("KEY_USERNAME", username);
                extras.putString("KEY_PASSWORD", password); //send variables with identifiers.
                extras.putString("KEY_GAME", "TPS");
                Log.d("username1", username);
                Log.d("password1", password);
                intent.putExtras(extras); //send variables to other screen
                startActivity(intent);
        }

                  //when button is pressed for TwoDS
                  public void TwoDS(View v) {
                      Intent intent = new Intent(Main_menu.this, StatMenu.class); //go to stat menu
                      Bundle extras = new Bundle(); //create new bundle to send variables accross screens
                      extras.putString("KEY_USERNAME", username); // send username over to
                      extras.putString("KEY_PASSWORD", password);
                      extras.putString("KEY_GAME", "2DS");//important to let game know what stats to bring up.
                      Log.d("username1", username);
                      Log.d("password1", password);
                      intent.putExtras(extras);//send the variables to the other screen
                      startActivity(intent);
             }


   }



