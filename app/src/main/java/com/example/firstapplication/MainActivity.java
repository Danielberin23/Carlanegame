package com.example.firstapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;
import java.util.Timer;

import Logic.GameManager;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView main_IMG_background;
    private FloatingActionButton[]  main_Action_Buttons;
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_Car_green;
    private ShapeableImageView[][] main_Obstacles_lane;
    final Handler handler = new Handler();
    GameManager gameManager;
    private Timer timer;
    private int score = 0;
    private int index = 0;
    String status = "";
    private final int numOfLives = 3;
    private final int numRows = 6;
    private final int numCol =5;
    private static final int DELAY = 700;
    private moveDecoretor moveDecoretor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        Glide
                .with(this)
                .load("https://opengameart.org/sites/default/files/background-1_0.png")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(main_IMG_background);
        gameManager = new GameManager(numOfLives, numRows, numCol);
        if (status.equals("false")) {
            moveDecoretor = new moveDecoretor(this, callBack_moves);
        }
        initViews();

    }
    private void clicked(boolean answer) {
        gameManager.moveCar(answer, gameManager.getCar_Position());
        setVisibility();
    }
    private void initViews() {
        if (status.equals("true")) {

            //left button
            main_Action_Buttons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked(true);
                }
            });

            //right button
            main_Action_Buttons[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked(false);
                }
            });
        }
        startTimer();
    }

    private moveDecoretor.CallBack_moves callBack_moves = new moveDecoretor.CallBack_moves() {
        @Override
        public void car1Step() {
            gameManager.setCar_Position(0);
            setVisibility();
        }

        @Override
        public void car2Step() {
            gameManager.setCar_Position(1);
            setVisibility();
        }

        @Override
        public void car3Step() {
            gameManager.setCar_Position(2);
            setVisibility();
        }

        @Override
        public void car4Step() {
            gameManager.setCar_Position(3);
            setVisibility();
        }

        @Override
        public void car5Step() {
            gameManager.setCar_Position(4);
            setVisibility();
        }
    };
    //
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    Runnable runnable = new Runnable() {
        public void run() {
            handler.postDelayed(this, DELAY);
            showObstacles();
        }
    };

    private void startTimer() {

        handler.postDelayed(runnable, DELAY);
    }

    private void stopTimer() {

        handler.removeCallbacks(runnable);
        handler.removeCallbacks(runnable);
    }
    private void updateUI() {
        if (numOfLives==0) {
            stopTimer();
            openScorePage();
        } else {
            for (int i = 0; i <(3-numOfLives); i++) {
                main_IMG_hearts[i].setVisibility(View.INVISIBLE);
            }
        }
        }


    private void openScorePage() {
        Intent intent = new Intent(this, ScoreMap.class);
        startActivity(intent);
        finish();
    }
    private void setVisibility(){
        main_Car_green[gameManager.getCar_Position()].setVisibility(View.VISIBLE);
        for (int i = 0; i < main_Car_green.length; i++) {
            if(i != gameManager.getCar_Position())
                main_Car_green[i].setVisibility(View.INVISIBLE);
        }
    }

    private void showObstacles() {
        for (int i = 0; i < numCol; i++){
            for(int j = 0; j < numRows; j++){
                if (gameManager.getMatOfObstacle()[j][i] == 1){
                    if(j != numRows-1){
                        gameManager.setActiveObstacles(j, i, 0);
                        gameManager.setActiveObstacles(j+1, i, 1);
                        if (i != numCol-1)
                            i++;
                    }
                    else {
                        gameManager.setActiveObstacles(j, i, 0);
                        if (gameManager.checkAccident(i) == 1) {
                            vibrate();
                            showToast("You lost a life");
                        }
                        updateUI();
                        if(numOfLives!=0)
                            gameManager.getNewObstacle();
                    }
                }
                else if(gameManager.getMatOfObstacle()[j][i] == 2){
                    if(j != numRows-1){
                        gameManager.setActiveObstacles(j, i, 0);
                        main_Obstacles_lane[i][j].setImageResource(R.drawable.barrier);
                        gameManager.setActiveObstacles(j+1, i, 2);
                        if (i != numCol-1)
                            i++;
                    }
                    else {
                        gameManager.setActiveObstacles(j, i, 0);
                        score += gameManager.checkCoins(i);

                        updateUI();
                    }
                }
            }
        }
        int randomCoin = new Random().nextInt(5);
        if(randomCoin<4)
            gameManager.getNewObstacle();
        else
            gameManager.getNewCoin();
        update();
    }

    @SuppressLint("ObsoleteSdkInt")


    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
    private void showToast(String string) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, string, duration);
        toast.show();
    }



    private void findViews() {

        main_IMG_background= findViewById(R.id.main_IMG_background);
        main_Car_green= new ShapeableImageView[]{
                findViewById(R.id.green_car_left),
                findViewById(R.id.green_car_centerL),
                findViewById(R.id.green_car_center),
                findViewById(R.id.green_car_centerR),
                findViewById(R.id.green_car_right)};
        main_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_heart1),
                findViewById(R.id.main_IMG_heart2),
                findViewById(R.id.main_IMG_heart3)};
        main_Action_Buttons= new FloatingActionButton[]{
                findViewById(R.id.main_Left_button),
                findViewById(R.id.main_Right_button)
        };
        findObstacles();
    }
    private void findObstacles(){
        int current, i, j, index = 1;
        main_Obstacles_lane = new ShapeableImageView[numRows][numCol];
        String rock = "game_IMG_stoneC";

        for (i = 0; i < numRows; i++){
            for(j = 0; j < numCol; j++){
                current = getResources().getIdentifier(rock + index, "id", getPackageName());
                main_Obstacles_lane[i][j] = findViewById(current);
                index++;
            }
        }
        for (i = 0; i < numRows; i++){
            for(j = 0; j < numCol; j++){
                gameManager.getMatOfObstacle()[i][j] = 0;
                main_Obstacles_lane[i][j].setVisibility(View.INVISIBLE);
            }
        }
        gameManager.getNewObstacle();
        update();
    }
    private void update()
    {
        score++;
        index++;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCol; j++)
            {
                if (gameManager.getMatOfObstacle()[i][j] == 1)
                {
                   main_Obstacles_lane[i][j].setImageResource(R.drawable.barrier);
                    main_Obstacles_lane[i][j].setVisibility(View.VISIBLE);
                }
                else  if (gameManager.getMatOfObstacle()[i][j] == 2)
                {
                    main_Obstacles_lane[i][j].setImageResource(R.drawable.img_coin);
                    main_Obstacles_lane[i][j].setVisibility(View.VISIBLE);
                }
                else if (gameManager.getMatOfObstacle()[i][j] == 0)
                    main_Obstacles_lane[i][j].setVisibility(View.INVISIBLE);
                else
                    main_Obstacles_lane[i][j].setVisibility(View.VISIBLE);
            }
        }

    }
}
