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

        textView = findViewById(R.id.txt1_splash);

        String text = "<font color=#34B6EC>Beyond</font> <font color=#F48325> The Action</font>";
        textView.setText(Html.fromHtml(text));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this, MainActivity.class));

            }
        },SPLASH_DISPLAY_LENGTH);

    }
}
