package com.sj.dailyplan.view.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.sj.dailyplan.R;


/**
 * Created by Administrator on 2017/7/2.
 */

public class SplashActivity extends Activity {

    private static final String TAG = "SplashActivity";
    private Button btn_skip;
    private Intent intent;
    private CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        intent = new Intent(this , MainActivity.class);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        timer = new CountDownTimer(3000 , 1000) {
            @Override
            public void onTick(long l) {

            }
            @Override
            public void onFinish() {
                    startActivity(intent);
                    SplashActivity.this.finish();
            }
        };
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(intent);
                    SplashActivity.this.finish();
                }
        });
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null)
            timer.cancel();
    }
}
