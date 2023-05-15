package com.example.firstapplication;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SignalGenerator.init(this);
    }
}