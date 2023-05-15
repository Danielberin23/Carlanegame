package Logic;

import java.util.Random;

public class GameManager {
    private int numOfLives;
    private int[] arrOfLives;
    private final int numRows;
    private final int numCol;



    private int[] arrOfCar;



    private int[][] matOfObstacle;
    private int car_Position;
    private boolean isCrash = false;



    private boolean isFinish = false;

    public GameManager (int numOfLives , int numRows , int numCol){
        this.numOfLives = numOfLives;
        this.numCol=numCol;
        this.numRows=numRows;

    }

    public void moveCar(boolean answer, int place)
    {
        if(place != 0 && place !=4)
        {
            if(answer == true)
                setCar_Position(place - 1);
            else setCar_Position(place+1);
        }
        else if(place == 0 && answer == false)
        {
            setCar_Position(1);
        }
        else if(place == 4 && answer == true)
        {
            setCar_Position(3);
        }
        else setCar_Position(place);
    }


    public void initItems() {
        initLives();
        initCars();
    }

    private void initCars() {
        arrOfCar = new int [numCol];
        for(int i=0; i<numCol; i++){
            arrOfCar[i]=0;
        }
        car_Position=2;
        arrOfCar[car_Position]=1;
    }


    private void initLives() {
        arrOfLives = new int[numOfLives];
        for (int i = 0; i < arrOfLives.length; i++) {
            arrOfLives[i] = 1;
        }
    }
    public int checkCoins (int coinPlace)
    {
        if(car_Position == coinPlace)
        {
            return 10;
        }
        return 0;
    }
    public int checkAccident(int ObstaclePlace)
    {
        if(car_Position == ObstaclePlace)
        {
            return 1;
        }
        return 0;
    }
    public void setActiveObstacles(int row, int col, int num){
        this.matOfObstacle[row][col] = num;
    }
    public void getNewObstacle(){
        setActiveObstacles(0, new Random().nextInt(numCol), 1);
    }

    public void getNewCoin(){

        setActiveObstacles(0, new Random().nextInt(numCol), 2);
    }
    public int getNumOfLives() {
        return numOfLives;
    }
    public int[] getLives() {
        return arrOfLives;
    }

    public void setNumOfLives(int numOfLives) {
        this.numOfLives = numOfLives;
    }

    public int getCar_Position() {
        return car_Position;
    }
    public boolean isGameFinish() {
        return isFinish;
    }

    public void setCar_Position(int car_Position) {
        this.car_Position = car_Position;
    }

    public boolean isCrash() {
        return isCrash;
    }

    public void setCrash(boolean crash) {
        isCrash = crash;
    }
    public int[] getArrOfCar() {
        return arrOfCar;
    }

    public void setArrOfCar(int[] arrOfCar) {
        this.arrOfCar = arrOfCar;
    }
    public int[][] getMatOfObstacle() {
        return matOfObstacle;
    }

    public void setMatOfObstacle(int[][] matOfObstacle) {
        this.matOfObstacle = matOfObstacle;
    }
    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

}
