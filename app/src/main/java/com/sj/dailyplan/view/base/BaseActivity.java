package com.sj.dailyplan.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sj.dailyplan.R;


/**
 * Created by Administrator on 2017/8/14.
 */

public class BaseActivity extends AppCompatActivity {

    private BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    public void initToolbar(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar == null) return;
        setSupportActionBar(toolbar);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        tv_title.setText(title);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.finish();
            }
        });
    }
}
