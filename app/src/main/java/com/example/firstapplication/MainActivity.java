package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton[]  main_Action_Buttons;
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_Car_green;

    private ShapeableImageView[] main_Obstacles_lane;

    private int car_Position=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setArrowKeyClickListeners();
    }
    //
    private void setArrowKeyClickListeners(){

        main_Action_Buttons[0].setOnClickListener(v->clickedActionLeft());//left button car
        main_Action_Buttons[1].setOnClickListener(v ->clickedActionRight() );


    }

    private void clickedActionRight() {
        main_Car_green[car_Position].setVisibility(View.INVISIBLE);
        car_Position++;
        if(car_Position<0)
            car_Position=2;
        else if(car_Position>2)
            car_Position=0;
        main_Car_green[car_Position].setVisibility(View.VISIBLE);
    }


    private void clickedActionLeft() {
        main_Car_green[car_Position].setVisibility(View.INVISIBLE);
        car_Position--;
        if(car_Position<0)
            car_Position=2;
        else if(car_Position>2)
            car_Position=0;
        main_Car_green[car_Position].setVisibility(View.VISIBLE);
    }

    private void findViews() {
        main_Car_green= new ShapeableImageView[]{
                findViewById(R.id.green_car_left),
                findViewById(R.id.green_car_center),
                findViewById(R.id.green_car_right)};
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)};
        main_Action_Buttons= new FloatingActionButton[]{
                findViewById(R.id.main_Left_button),
                findViewById(R.id.main_Right_button)
        };
    }
}