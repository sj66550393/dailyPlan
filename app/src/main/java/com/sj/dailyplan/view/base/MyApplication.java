package com.sj.dailyplan.view.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sj.diary.mode.diary.DaoMaster;
import com.sj.diary.mode.diary.DaoSession;

/**
 * Created by Administrator on 2017/7/9.
 */

public class MyApplication extends Application {

    private static final String TAG = "Application";

    private DaoSession mSession;

    @Override
    public void onCreate() {
        setupDatabase();
        Log.d(TAG , "onCreate()");
        super.onCreate();
    }

    private void setupDatabase(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this , "bear-db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        mSession = master.newSession();
    }

    public DaoSession getDaoSesiion(){
        return mSession;
    }
}
