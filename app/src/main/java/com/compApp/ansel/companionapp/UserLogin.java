package com.compApp.ansel.companionapp;


/*

CHARLIE ANSELL
07/04/2019

   This java file allows the user to login to the system by entering their email and their password.
   these are checked against the database through the help of a php script
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UserLogin extends AppCompatActivity implements View.OnClickListener{

    public static final String LOGIN_URL = "http://u530535384.hostingerapp.com/login.php"; //php script to be redidrected to

    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private String username;
    private String password;
    public Boolean CheckEditText;
    private int tries=3;

    //On create initialise all views.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextUsername = (EditText) findViewById(R.id.usernameInput);
        editTextPassword = (EditText) findViewById(R.id.passInput);
        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //make input type password
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        //set on click listener to buttonlogin
        buttonLogin.setOnClickListener(this);
    }

    //login method posts values to php script and server gives response
    private void userLogin() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Validating please wait");
        dialog.setProgressStyle(dialog.STYLE_SPINNER); //show dialog spinner to user.
        dialog.show();

        //get username and password.
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        //create string request and post to php script.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {

            //on server response
                    @Override
                    public void onResponse(String response) {

                        //validate returned value
                        if(response.trim().contains("success")){ //if server response is success then log in user
                            openMenu();
                            dialog.dismiss();
                        }else{ //if response isn't success then show the user and decrement their login counter
                            Toast.makeText(UserLogin.this,response,Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            tries--;
                            if(tries<3){ //if tries are less than 3. show the user.
                                Toast.makeText(UserLogin.this,Integer.toString(tries) + " attempts remaining",Toast.LENGTH_LONG).show();
                                if(tries<=0){ //if no tries left lock them out
                                    Toast.makeText(UserLogin.this,"Account locked - too many failed login attempts",Toast.LENGTH_LONG).show();
                                    buttonLogin.setEnabled(false);

                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
            //if theres an error with connecting to server, show it.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserLogin.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            //send all values to server with hashmap
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //signature method of stringrequest to post values to script
                Map<String,String> map = new HashMap<String,String>();
                map.put("device","android");
                map.put(KEY_USERNAME,username);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        //reset the network timeout policy
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest); //create request queue and add string request to it.
    }

    //open the menu class
    private void openMenu(){
        Intent intent = new Intent(this, Main_menu.class);
        Bundle extras = new Bundle();//create bundle to intent values to main menu class
        extras.putString("KEY_USERNAME",username);
        extras.putString("KEY_PASSWORD",password); //prepare values for sending
        intent.putExtras(extras);//send values to other screen.
        startActivity(intent);
    }

    //override method for onclick listener for button login
    @Override
    public void onClick(View v) {
        checkIfEditextsAreEmpty(); //check if edittexts are not empty, if not call userlogin method
        if(CheckEditText) {
            userLogin();
        }
        else {
            //if edittexts are empty let user know
            Toast.makeText(UserLogin.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
        }

    }

    //check if edit texts are empty.
    private void checkIfEditextsAreEmpty(){
        // Getting values from EditText.
        String emailHolder = editTextUsername.getText().toString().trim();
        String PasswordHolder = editTextPassword.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if (TextUtils.isEmpty(emailHolder) || TextUtils.isEmpty(PasswordHolder)) {

            // If any of EditText is empty then set variable value as False.
            CheckEditText = false;

        } else {

            // If any of EditText is filled then set variable value as True.
            CheckEditText = true;
        }
    }

}

