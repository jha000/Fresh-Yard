package com.example.milkaggregatorapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class spash extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT=4000;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        lottie=findViewById(R.id.lottie);

//        lottie.animate().translationX(3000).setDuration(3000).setStartDelay(3100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(spash.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}