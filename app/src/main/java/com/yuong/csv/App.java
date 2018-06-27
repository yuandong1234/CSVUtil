package com.yuong.csv;

import android.app.Application;

import com.yuong.csv.file.FileStorageManager;

/**
 * Created by yuandong on 2018/6/27.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileStorageManager.getInstance().init(this);
    }
}
