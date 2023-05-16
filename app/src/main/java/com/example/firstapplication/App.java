package com.example.firstapplication;

import android.app.Application;

import com.google.gson.Gson;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MySPV3.MySPv3.init(this);
        String js = MySPV3.MySPv3.getInstance().getString("MY_DB", "");
        myDB md = new Gson().fromJson(js, myDB.class);

        if (md == null) {
            myDB myDB = new myDB();
            String json = new Gson().toJson(myDB);
            MySPV3.MySPv3.getInstance().putString("MY_DB", json);
        }
    }
}