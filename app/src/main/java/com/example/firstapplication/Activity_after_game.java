package com.example.firstapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.Comparator;

public class Activity_after_game extends AppCompatActivity
{

    private AppCompatImageView street_IMG_background;
    private TextView gameOver_LBL_score;
    private TextView gameOver_LBL_result;
    private EditText gameOver_LBL_name;
    private MaterialButton gameOver_BTN_saveRecord;
    private MaterialButton gameOver_BTN_back;

    private GPSTracker gpsService;

    private com.example.firstapplication.myDB myDB;

    private String playerName;
    private int score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_game);
        GameUtils.hideSystemUI(this);

        score = getIntent().getExtras().getInt("score");

        findView();

        gameOver_LBL_result.setText("Score: " + score);
        initView();

    }

    private void initView() {
        gameOver_BTN_back.setOnClickListener(view -> finish());
        gameOver_BTN_saveRecord.setOnClickListener(view -> {
            double latitude = 0.0;
            double longitude = 0.0;

            playerName = gameOver_LBL_name.getText().toString();

            gpsService = new GPSTracker(Activity_after_game.this);
            if (gpsService.canGetLocation())
            {
                latitude = gpsService.getLatitude();
                longitude = gpsService.getLongitude();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            } else {
                gpsService.showSettingsAlert();
            }
            // * End of Location Service

            gameOver_LBL_name.setVisibility(View.INVISIBLE);
            gameOver_BTN_saveRecord.setVisibility(View.INVISIBLE);

            saveRecord(playerName, score, longitude, latitude);
        });
    }

    private void findView() {

        gameOver_LBL_result = findViewById(R.id.gameOver_LBL_result);
        gameOver_LBL_name = findViewById(R.id.gameOver_LBL_name);
        street_IMG_background = findViewById(R.id.game_IMG_background);
        gameOver_LBL_score = findViewById(R.id.gameOver_LBL_score);

        gameOver_BTN_saveRecord = findViewById(R.id.gameOver_BTN_saveRecord);
        gameOver_BTN_back = findViewById(R.id.gameOver_BTN_back);


    }

    private void saveRecord(String player_name, int score, double longitude, double latitude) {

        String js = MySPV3.MySPv3.getInstance().getString("MY_DB", "");
        myDB = new Gson().fromJson(js, myDB.class);

        myDB.getRecords().add(new Score()
                .setName(player_name)
                .setScore(score)
                .setLat(latitude)
                .setLon(longitude)
        );

        myDB.getRecords().sort(new SortByScore());

        String json = new Gson().toJson(myDB);
        MySPV3.MySPv3.getInstance().putString("MY_DB", json);
    }
}
class SortByScore implements Comparator<Score> {

    @Override
    public int compare(Score rec1, Score rec2) {

        return rec2.getScore() - rec1.getScore();
    }
}