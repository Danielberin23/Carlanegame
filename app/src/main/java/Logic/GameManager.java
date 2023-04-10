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

    public void moveCar(String direction){
        int nextColumn;

        if(direction.equals("left")){
            nextColumn = car_Position -1;
            if(nextColumn >= 0){
                arrOfCar[car_Position] = 0;
                arrOfCar[nextColumn] = 1;
                car_Position = nextColumn;
            }
        }

        if(direction.equals("right")){
            nextColumn = car_Position +1;
            if(nextColumn  < numCol){
                arrOfCar[car_Position] = 0;
                arrOfCar[nextColumn] = 1;
                car_Position = nextColumn;
            }
        }

    }
    public void initItems() {
        initLives();
        initObstacles();
        initCars();
    }

    private void initCars() {
        arrOfCar = new int [numCol];
        for(int i=0; i<numCol; i++){
            arrOfCar[i]=0;
        }
        car_Position=1;
        arrOfCar[1]=car_Position;
    }

    private void initObstacles() {
        matOfObstacle = new int[numRows][numCol];

        for(int i=0; i< numRows; i++){
            for (int j=0; j<numCol;j++){
                matOfObstacle[i][j] = 0;
            }
        }
    }
    public void updateBoard() {
        updateObstacles();
        addNewObstacle();
    }
    private void addNewObstacle() {
        int randomCol = new Random().nextInt(numCol);
        
        for (int i = 0; i < numCol; i++) {
            if(randomCol == i) {
                matOfObstacle[0][i] = 1;
            }
            else{
                matOfObstacle[0][i] = 0;
            }
        }
    }

    private void initLives() {
        arrOfLives = new int[numOfLives];
        for (int i = 0; i < arrOfLives.length; i++) {
            arrOfLives[i] = 1;
        }
    }
    private void updateObstacles() {
        for (int i = numRows - 1; i >= 0 ; i--) {
            for (int j = 0; j < numCol; j++) {
                if( i == numRows - 1 && matOfObstacle[i][j] == 1){
                    matOfObstacle[i][j] = 0;
                    if(j == car_Position){
                        isCrash = true;
                        numOfLives = numOfLives -1;
                        arrOfLives[numOfLives] = 0;
                        if(numOfLives == 0){
                            isFinish = true;
                        }
                    }
                }
                else if(i != numRows - 1){
                    matOfObstacle[i+1][j]=matOfObstacle[i][j];
                }
            }
        }
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
