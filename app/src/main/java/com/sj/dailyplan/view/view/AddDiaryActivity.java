package com.sj.dailyplan.view.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;


import com.sj.dailyplan.R;
import com.sj.dailyplan.view.base.BaseActivity;
import com.sj.dailyplan.view.view.fragment.addDiaryFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/9.
 */

public class AddDiaryActivity extends BaseActivity {

    private static final String TAG = "AddDiaryActivity";
    private Fragment mFragment;
    private static final int ADD_DIARY = 0;
    private static final int PRE_DIARY = 1;


    public static void previewDiary(Context context , String uid){
        Intent intent = new Intent(context , AddDiaryActivity.class);
        intent.putExtra("action" , PRE_DIARY);
        intent.putExtra("uid" , uid);
        context.startActivity(intent);
    }

    public static void adddiary(Context context){
        Intent intent = new Intent(context , AddDiaryActivity.class);
        intent.putExtra("action" , ADD_DIARY);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddiary);
        initContainer();

    }

    private void initContainer(){
        Intent intent = getIntent();
        if(intent.getIntExtra("action" , ADD_DIARY) == ADD_DIARY){
            initAddDiary();
        }else{
            initPreDiary(intent.getStringExtra("uid"));
        }
        if(mFragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_container , mFragment).commit();
        }
    }

    private void initAddDiary(){
        mFragment = addDiaryFragment.newInstant();
    }

    private void initPreDiary(String uid){

    }

    private String getCurTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
