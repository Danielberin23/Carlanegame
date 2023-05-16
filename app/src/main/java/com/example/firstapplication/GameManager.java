package com.example.firstapplication;


import java.util.Random;
public class GameManager
{


    public final int ROWS = 6;
    public final int COLS = 5;
    public final int Lifes = 3;
    private int wrong = 0;
    private int life;
    public int carPlace;
    public int[][] activeObstacles = new int[ROWS][COLS];

    public GameManager() {
        this.life = Lifes;
    }

    public void setCarPlace(int place) {
        this.carPlace = place;
    }
    public int getCarPlace(){
        return this.carPlace;
    }

    public void setActiveObstacles(int row, int col, int num){
        this.activeObstacles[row][col] = num;
    }


    public int getWrong() {
        return wrong;
    }

    public void setPlace(boolean answer, int place) {
        if(place != 0 && place != 4){
            if(answer)
                setCarPlace(place-1);
            else setCarPlace(place+1);
        }
        else if(place == 0 && answer){
            setCarPlace(1);
        }
        else if(place == 4 && !answer){
            setCarPlace(3);
        }
        else setCarPlace(place);
    }

    public int checkAccident(int obstaclePlace) {
        if(carPlace == obstaclePlace) {
            wrong++;
            return 1;
        }
        return 0;
    }

    public int checkCoins(int coinPlace) {
        if(carPlace == coinPlace) {
            return 10;
        }
        return 0;
    }


    public void getNewObstacle(){
        setActiveObstacles(0, new Random().nextInt(COLS), 1);
    }

    public void getNewCoin(){

        setActiveObstacles(0, new Random().nextInt(COLS), 2);
    }


    public boolean isLose() {
        return wrong == life;
    }

}
