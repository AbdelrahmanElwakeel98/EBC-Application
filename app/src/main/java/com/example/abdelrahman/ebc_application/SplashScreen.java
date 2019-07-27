package com.example.abdelrahman.ebc_application;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private TextView textView;

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                SplashScreen.this.finish();

            }
        },SPLASH_DISPLAY_LENGTH);

    }
}
