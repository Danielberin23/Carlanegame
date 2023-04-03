package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {
    private AppCompatImageView main_Green_car;
    private FloatingActionButton[]  main_Action_Buttons;
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_Car_green;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviews();
    }

    private void findviews() {
    }
}