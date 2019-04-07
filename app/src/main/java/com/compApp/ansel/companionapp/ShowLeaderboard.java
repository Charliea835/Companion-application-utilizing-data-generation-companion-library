package com.compApp.ansel.companionapp;

/*

Charlie Ansell
07/04/2019

This scipt shows the leaderboard view for the survival shooter or space shooter games.



 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;     //import libraries needed
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;//third party library for image circle views.

public class ShowLeaderboard extends AppCompatActivity {
    RequestQueue requestQueue;
    String url = "http://u530535384.hostingerapp.com/Leaderboard.php";    //need two URLs as we are sending two requests
    String url1 = "http://u530535384.hostingerapp.com/getUserRank.php";
    String username;
    String password;
    String game;
    String score;
    String rank;
    TextView accountText;
    TextView rankView;
    TextView usernameView;
    TextView scoreView;
    TextView playerRank;
    CircleImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showleaderboard);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        username = extras.getString("KEY_USERNAME");
        password = extras.getString("KEY_PASSWORD");
        game = extras.getString("KEY_GAME");
        Log.d("username3", username);
        Log.d("password3", password);
        Log.d("game", game);
        accountText = findViewById(R.id.accountName);
        accountText.setText(username);
        rankView = findViewById(R.id.rankView);
        usernameView = findViewById(R.id.usernameView);
        scoreView = findViewById(R.id.scoreView);
        playerRank=findViewById(R.id.PlayerRank);
        image = findViewById(R.id.imageview_account_profile);

        //if the game is TPS then we set the image at the top of the screen to be the survival_shooter image.
        if(game.equals("TPS")){

            image.setImageResource(R.drawable.survival_shooter);
        }
        //if the game is 2DS we set the image to be the space shooter image.
        else if (game.equals("2DS")){
            image.setImageResource(R.drawable.space_shooter);
        }

        getRank(); //call the get rank to get the users ranking
        getLeaderboard(); //call the get leaderboard to display the leaderboard.
    }


    //function to get the leaderboard rankings.
    public void getLeaderboard() {

        //create a string request and send to url containing php file to proccess and echo back leaderboard rankings.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            //when we get a response from the server.
            @Override
            public void onResponse(String response) {
                try {
                    //response will be in JSON so we create a JSON object to decipher response.
                    Log.d("json", response);
                    //Creating JsonObject from response String
                    JSONObject jsonObject = new JSONObject(response);
                    //extracting json array from response string
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    String rankOutput = "";
                    String usernameOutput = "";
                    String scoreOutput = "";
                    //Iterate through JSON array and retrieve values
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonRow = jsonArray.getJSONObject(i); //get JSON values at each index of array.
                        username = jsonRow.getString("username");
                        score = jsonRow.getString("score");
                        rank = jsonRow.getString("rank");

                        //add the string format of the values to strings to be shown on textViews
                        rankOutput += String.format("%s \n", rank);
                        usernameOutput += String.format("%s \n", username);
                        scoreOutput += String.format("%s \n", score);

                        //set the values to the textViews
                        rankView.setText(rankOutput);
                        usernameView.setText(usernameOutput);
                        scoreView.setText(scoreOutput);
                    }


                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            //Hashmap to send values to server with identifiers.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("game", game);
                parameters.put("username", username);
                parameters.put("password", password);
                return parameters;
            }

        };
        requestQueue.add(stringRequest);
    }


    //get rank method gets the rank of the current user in the leaderboard rankings and displays it.
    public void getRank() {

        //create String request and send it to the second URL which goes to php script to process and echo back result.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {

            //on response from server
            @Override
            public void onResponse(String response) {
                try {
                    //create JSON object and parse JSON
                    Log.d("json1", response);
                    //Creating JsonObject from response String
                    JSONObject jsonObject = new JSONObject(response);
                    //extracting json array from response string
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    String rankOutput = "";

                    //Iterate through JSON array and get the value.
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonRow = jsonArray.getJSONObject(i); //get JSON values at each index of array
                        rank = jsonRow.getString("rank");

                        if(rank!=""){ //if rank recieved isnt null
                            rankOutput += String.format("You rank %s on the leaderboard", rank); //set the rank output string.
                        }
                        else{
                            rankOutput += String.format("No data recorded");//if there is no value say no data recorded (not working)
                        }

                        playerRank.setText(rankOutput); //display to screen.
                    }


                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            //send hashmap containing values to server.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("game", game);
                parameters.put("username", username);
                parameters.put("password", password);
                return parameters;
            }

        };
        requestQueue.add(stringRequest); //add this request to the request queue.


    }
}

