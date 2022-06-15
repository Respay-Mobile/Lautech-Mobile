package com.example.lautechmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //countdown timer object CountDOwnTimer(How long should timer run, Time interval after which ontick should be called)
        cd = new CountDownTimer(1000, 2000) {
            @Override
            //onTick(seconds remaining)
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() {
                //Once timer is done start "processing login"
                processLogin();
            }
        };
    }

    private void processLogin(){
        //Shared preferences is a dictionary for storing and retrieving data on the device
        //MODE_PRIVATE is used for security
        SharedPreferences sharedPreferences = getSharedPreferences("ON_BOARDING", MODE_PRIVATE);
        String ob = sharedPreferences.getString("on_boarding", "");

        //if ob string is empty move to the onboarding screen class
        //i.e if this is user's first time show them the on boarding screen
        if(ob == null){
            startActivity(new Intent(getApplicationContext(), OnBoardingScreenActivity.class));
        } else if(ob.matches("")){
            startActivity(new Intent(getApplicationContext(), OnBoardingScreenActivity.class));
        } else {
            //If this is not user's first time check if user has signed in before and has their email saved
            SharedPreferences sharedPreferences1 = getSharedPreferences("LOGIN-STATE", MODE_PRIVATE);
            String loginState = sharedPreferences1.getString("login_state", "");
            startActivity(new Intent(getApplicationContext(), OnBoardingScreenActivity.class));

            /*
            TODO: handle login later
            //If user email is not saved move them to the loginpage1 activity where they input email and password
            if(loginState == null){
                startActivity(new Intent(getApplicationContext(), LoginPage1.class));
            } else if(loginState.matches("")){
                startActivity(new Intent(getApplicationContext(), LoginPage1.class));
            } else if(loginState.matches("TRUE")){

                //If user email is saved move them to the loginpage activity where they input only password
                startActivity(new Intent(getApplicationContext(), LoginPage.class));
            } else if(loginState.matches("FALSE")){
                startActivity(new Intent(getApplicationContext(), LoginPage1.class));
            } else {
                startActivity(new Intent(getApplicationContext(), LoginPage1.class));
            }

             */
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cd.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cd.cancel();
    }

    //If user press back button cancel timer
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cd.start();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        cd.cancel();
    }

    //If all is closed cancel timer
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cd.cancel();
    }

}