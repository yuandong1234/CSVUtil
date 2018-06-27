package com.yuong.csv.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by yuandong on 2018/6/27.
 */

public class FileStorageManager {
    private static String TAG = FileStorageManager.class.getSimpleName();
    private static final FileStorageManager sManager = new FileStorageManager();

    private Context mContext;

    public static FileStorageManager getInstance() {
        return sManager;
    }

    private FileStorageManager() {

    }

    public void init(Context context) {
        this.mContext = context;
    }


    public File getFileByName(String fileName) {

        File parent;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            parent = mContext.getExternalCacheDir();
        } else {
            parent = mContext.getCacheDir();
        }

        File file = new File(parent, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                Log.i(TAG,"create new file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i(TAG,"file：" + file);
        return file;

    }
}
