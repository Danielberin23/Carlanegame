package com.example.firstapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;


public class MenuActivity extends AppCompatActivity
{

    private Button main_BTN_start;
    private Button main_BTN_score;
    private MaterialButton main_BTN_sensors;
    private MaterialButton main_BTN_buttons;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        findViews();
        initViews();
        clicked();
    }

    private void findViews()
    {
        main_BTN_start = findViewById(R.id.main_btn_start);
        main_BTN_score = findViewById(R.id.main_btn_score);
        main_BTN_sensors = findViewById(R.id.main_BTN_sensor);
        main_BTN_buttons = findViewById(R.id.main_BTN_button);

    }

    private void initViews() {
        main_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked();
            }
        });

        main_BTN_score.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openScorePage();
            }
        });

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openScorePage()
    {
        Intent intent = new Intent(this, Activity_TOP10.class);
        startActivity(intent);
    }

    private void openGamePage(String chose)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.KEY_STATUS,chose);
        startActivity(intent);
    }

    private void clicked(){
        main_BTN_buttons.setVisibility(View.VISIBLE);
        main_BTN_sensors.setVisibility(View.VISIBLE);
        main_BTN_score.setOnClickListener(view -> openScorePage());
        initNextViews();
    }

    private void initNextViews()
    {
        main_BTN_buttons.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGamePage("true");
            }
        });

        main_BTN_sensors.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGamePage("false");
            }
        });


    }
}
