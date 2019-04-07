package com.compApp.ansel.companionapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/*

CHARLIE ANSELL
07/04/2019


This script shows the user their statistics to screen.

 */

public class ShowStats extends AppCompatActivity {
    RequestQueue requestQueue;
    String url = "http://u530535384.hostingerapp.com/ShowUserStats.php";



    //declare the textviews
    TextView accountText;
    TextView killsValue;
    TextView bulletsFiredValue;
    TextView bulletsHitValue;
    TextView bulletsMissedValue;
    TextView scoreValue;
    TextView shotAccuracyValue;
    TextView spmValue;
    TextView timePlayedValue;

    String username;
    String password;
    CircleImageView image;
    String game;
    String kills;
    String bulletsFired;
    String bulletsHit;
    String bulletsMissed;
    String score;
    String shotAccuracy;
    String spm;
    String timePlayed;

    //when the screen starts.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showstats); //get the correct layout.
        requestQueue = Volley.newRequestQueue(getApplicationContext()); //create a new request queue to hold requests
        Bundle extras = getIntent().getExtras(); //get parameteres sent from other screens.
        username= extras.getString("KEY_USERNAME");
        password = extras.getString("KEY_PASSWORD");
        game= extras.getString("KEY_GAME");
        Log.d("username3",username);
        Log.d("password3",password);
        Log.d("game",game);


        //initialise all textViews
        accountText = findViewById(R.id.accountName);
        killsValue = findViewById(R.id.killValue);
        bulletsFiredValue=findViewById(R.id.bulletsFiredValue);
        bulletsHitValue=findViewById(R.id.bulletsHitValue);
        bulletsMissedValue=findViewById(R.id.bulletsMissedValue);
        scoreValue=findViewById(R.id.scoreValue);
        shotAccuracyValue=findViewById(R.id.shotAccuracyValue);
        spmValue=findViewById(R.id.scorePerMinValue);
        timePlayedValue=findViewById(R.id.timePlayedValue);
        image = findViewById(R.id.imageview_account_profile);
        accountText.setText(username);

        //ig the game is TPS then show survival shooter image.
        if(game.equals("TPS")){

            image.setImageResource(R.drawable.survival_shooter);
        }
        //if the game is 2DS then show space shooter image.
        else if (game.equals("2DS")){
            image.setImageResource(R.drawable.space_shooter);
        }

        getPlayersData(); //call function to get the playersData.
    }

    //get the players data.
    public void getPlayersData() {

        //create the string request to send to the server.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            //on resposne from the server.
            @Override
            public void onResponse(String response) {
                try {

                    //parse the JSON response.
                    Log.d("json", response);
                    //Creating JsonObject from response String
                    JSONObject jsonObject = new JSONObject(response);
                    //extracting json array from response string
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    JSONObject jsonRow = jsonArray.getJSONObject(0);


                    //get value from jsonRow
                     kills = jsonRow.getString("kills");
                     bulletsFired = jsonRow.getString("bulletsFired");
                     bulletsHit = jsonRow.getString("bulletsHit");
                     bulletsMissed = jsonRow.getString("bulletsMissed");
                     score = jsonRow.getString("score");
                     shotAccuracy = jsonRow.getString("hitAccuracy");
                     spm = jsonRow.getString("scorePerMinute");
                     timePlayed = jsonRow.getString("timePlayed");


                     //set all of the textviews to the values.
                     killsValue.setText(kills);
                     bulletsFiredValue.setText(bulletsFired);
                     bulletsHitValue.setText(bulletsHit);
                     bulletsMissedValue.setText(bulletsMissed);
                     scoreValue.setText(score);
                     shotAccuracyValue.setText(shotAccuracy + "%");
                     spmValue.setText(spm);
                     timePlayedValue.setText(timePlayed);

                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            //send the hashmap with values to the server.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("game",game);
                parameters.put("username",username);
                parameters.put("password",password);
                return parameters;
            }

        };
        requestQueue.add(stringRequest); //add the string request to the requestQueue.
    }

}
