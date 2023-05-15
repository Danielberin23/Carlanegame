package com.example.firstapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Timer;
import java.util.TimerTask;

import Logic.GameManager;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton[]  main_Action_Buttons;
    private ShapeableImageView[] main_IMG_hearts;
    private ShapeableImageView[] main_Car_green;
    private ShapeableImageView[][] main_Obstacles_lane;
    GameManager gameManager;
    private Timer timer;
    private final int numOfLives = 3;
    private final int numRows = 6;
    private final int numCol =3;
    private static final int DELAY = 700;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setArrowKeyClickListeners();
        gameManager = new GameManager(numOfLives, numRows, numCol);
        gameManager.initItems();

    }
    //
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> updateUI());

            }
        }, DELAY, DELAY);
    }
    private void updateUI() {
        gameManager.updateBoard();
        boolean isFinish = gameManager.isGameFinish();

        if (isFinish) {
            //Game Over!
            SignalGenerator.getInstance().toast("Game Over",Toast.LENGTH_SHORT);
            SignalGenerator.getInstance().vibrate(500);
            gameManager.updateBoard();
            gameManager.setNumOfLives(3);//return to full life
            gameManager.initItems();// restart board
            gameManager.setFinish(false);//restart game
        } else {
            boolean isCrashed = gameManager.isCrash();
            if (isCrashed) {
                showLives();
                SignalGenerator.getInstance().toast("Lost life!",Toast.LENGTH_SHORT);
                SignalGenerator.getInstance().vibrate(500);
                gameManager.setCrash(false);
            }
        }

        showObstacles();
    }

    private void showObstacles() {
        int[][] matObstacles = gameManager.getMatOfObstacle();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCol; j++) {
                if (matObstacles[i][j] == 1) {
                    main_Obstacles_lane[i][j].setImageResource(R.drawable.barrier);
                    main_Obstacles_lane[i][j].setVisibility(View.VISIBLE);
                }
                else  {
                    main_Obstacles_lane[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")

    private void setArrowKeyClickListeners(){

        main_Action_Buttons[0].setOnClickListener(v->clickedActionLeft());//left button car
        main_Action_Buttons[1].setOnClickListener(v ->clickedActionRight() );//right button


    }

    private void clickedActionRight() {
        gameManager.moveCar("right");
        showCar();
    }



    private void clickedActionLeft() {
        gameManager.moveCar("left");
        showCar();

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
        findObstacles();
    }
    private void findObstacles(){
        main_Obstacles_lane = new ShapeableImageView[][]{
                {findViewById(R.id.main_IMG_Barrier_11),
                        findViewById(R.id.main_IMG_Barrier_21),
                        findViewById(R.id.main_IMG_Barrier_31),}
                ,
                {findViewById(R.id.main_IMG_Barrier_12),
                        findViewById(R.id.main_IMG_Barrier_22),
                        findViewById(R.id.main_IMG_Barrier_32),
                },
                {findViewById(R.id.main_IMG_Barrier_13),
                        findViewById(R.id.main_IMG_Barrier_23),
                        findViewById(R.id.main_IMG_Barrier_33),},

                {findViewById(R.id.main_IMG_Barrier_14),
                        findViewById(R.id.main_IMG_Barrier_24),
                        findViewById(R.id.main_IMG_Barrier_34),
                        },
                {findViewById(R.id.main_IMG_Barrier_15),
                        findViewById(R.id.main_IMG_Barrier_25),
                        findViewById(R.id.main_IMG_Barrier_35),
                       },
                {findViewById(R.id.main_IMG_Barrier_16),
                        findViewById(R.id.main_IMG_Barrier_26),
                        findViewById(R.id.main_IMG_Barrier_36),
                }
        };
    }
}