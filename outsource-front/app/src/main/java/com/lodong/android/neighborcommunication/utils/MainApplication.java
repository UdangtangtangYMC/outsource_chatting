package com.lodong.android.neighborcommunication.utils;

import android.app.Application;

public class MainApplication extends Application {
    private static final String TAG = MainApplication.class.getSimpleName();
    private static MainApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static MainApplication getInstance(){return mInstance;}
}
