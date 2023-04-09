package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import Logic.GameManager;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton[]  main_Action_Buttons;
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_Car_green;
    private ShapeableImageView[] main_Obstacles_lane;
    GameManager gameManager;
    private int numOfLives = 3;
    private int numRows = 6;
    private int numCol =3;
    private static final int DELAY = 1000;
    private int car_Position=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        setArrowKeyClickListeners();
    }
    //


    private void updateUI() {
        gameManager.updateBoard();
        boolean isFinish = gameManager.isGameFinish();

        if (isFinish) {
            showToast("Game Over");
            makeVibrate();

        } else {
            boolean isCrashed = gameManager.isCrash();
            if (isCrashed) {
                showLives();
                showToast("!Lost Life!");
                gameManager.setCrash(false);
                makeVibrate();
            }
        }
        showObstacles();
    }

    private void showObstacles() {
        int[][] matObstacles = gameManager.getMatOfObstacle();
        int height=0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCol; j++) {
                if (matObstacles[i][j] == 1) {
                    main_Obstacles_lane[i].setImageResource(R.drawable.barrier);
                    main_Obstacles_lane[i].setVisibility(View.VISIBLE);
                }
                else  {
                    main_Obstacles_lane[i].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void makeVibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
    private void setArrowKeyClickListeners(){

        main_Action_Buttons[0].setOnClickListener(v->clickedActionLeft());//left button car
        main_Action_Buttons[1].setOnClickListener(v ->clickedActionRight() );//right button


    }

    private void clickedActionRight() {
        gameManager.moveCar("right");
        showCar();
    }



    private void clickedActionLeft() {

    }
    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

    }
    private void showLives() {
        int[] managerLives = gameManager.getLives();
        for (int i = 0; i < gameManager.getLives().length; i++) {
            if (managerLives[i] == 1) {
                main_IMG_hearts[i].setVisibility(View.VISIBLE);
            } else {
                main_IMG_hearts[i].setVisibility(View.INVISIBLE);
            }
        }
    }
    private void showCar(){
        int [] managerCar = gameManager.getArrOfCar();
        for (int i = 0; i < numCol; i++) {
            if(managerCar[i] == 1){
                main_Car_green[i].setVisibility(View.VISIBLE);
            }else{
                main_Car_green[i].setVisibility(View.INVISIBLE);
            }
        }
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
    private void findObstocales(){

    }
}