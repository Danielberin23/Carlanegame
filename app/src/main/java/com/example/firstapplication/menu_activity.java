package com.example.firstapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;

public class menu_activity extends AppCompatActivity {
    private AppCompatImageView main_IMG_background;

    private Button main_BTN_start;
    private Button main_BTN_score;
    private MaterialButton main_BTN_sensors;
    private MaterialButton main_BTN_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        findViews();


//        Glide
//                .with(this)
//                .load("https://ibb.co/X4CKNdJ")
//                .centerCrop()
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(main_IMG_background);

    }

    private void findViews() {
        main_BTN_start = findViewById(R.id.main_btn_start);
        main_BTN_score = findViewById(R.id.main_btn_score);
        main_BTN_sensors = findViewById(R.id.main_BTN_sensor);
        main_BTN_buttons = findViewById(R.id.main_BTN_button);
    }


}