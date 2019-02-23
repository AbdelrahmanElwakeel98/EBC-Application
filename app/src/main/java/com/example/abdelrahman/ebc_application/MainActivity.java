package com.example.abdelrahman.ebc_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Layout Variables
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize references to views
        gridLayout = findViewById(R.id.grid_dashboard);

        int childCount = gridLayout.getChildCount();

        for (int i= 0; i < childCount; i++){
            CardView container = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            container.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    switch (finalI){
                        case 0: Toast.makeText(MainActivity.this, "1", Toast.LENGTH_LONG).show();
                            return;

                        case 1: Toast.makeText(MainActivity.this, "2", Toast.LENGTH_LONG).show();
                            return;

                        case 2: startActivity(new Intent(MainActivity.this, Board.class));
                            return;

                        case 3: Toast.makeText(MainActivity.this, "4", Toast.LENGTH_LONG).show();
                            return;

                        case 4: Toast.makeText(MainActivity.this, "5", Toast.LENGTH_LONG).show();
                            return;

                        case 5: startActivity(new Intent(MainActivity.this, Feedback.class));
                            return;

                    }
                }
            });
        }

      /*  Intent intent = new Intent(MainActivity.this, Board.class);
        startActivity(intent);*/
    }
}
