package com.sj.dailyplan.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sj.diary.R;
import com.sj.diary.base.MyApplication;
import com.sj.diary.mode.diary.DaoSession;
import com.sj.diary.mode.diary.Diary;
import com.sj.diary.mode.diary.DiaryDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/9.
 */

public class AddDiaryActivity extends AppCompatActivity {

    private static final String TAG = "AddDiaryActivity";
    private static final int ADD_DIARY = 0;
    private static final int PRE_DIARY = 1;

    private EditText ed_title;
    private EditText ed_text;
    private Toolbar mToolBar;
    private ImageButton btn_back;
    private TextView tv_title;
    private TextView tv_extend;
    private DaoSession mDaoSession;
    private DiaryDao dao;
    private MyApplication myApplication;



    public static void previewDiary(Context context , int uid){
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
        myApplication = (MyApplication) getApplication();
        setContentView(R.layout.activity_adddiary);
        ed_text = (EditText) findViewById(R.id.ed_text);
        ed_title = (EditText) findViewById(R.id.ed_title);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDiaryActivity.this.finish();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("添加日记");
        tv_extend = (TextView) findViewById(R.id.tv_extend);
        tv_extend.setText("保存");
        tv_extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG , "onClick");
                saveDiary();
            }
        });
        mDaoSession = myApplication.getDaoSesiion();
        dao = mDaoSession.getDiaryDao();

        Intent intent = getIntent();
        if(intent.getIntExtra("action" , ADD_DIARY) == PRE_DIARY){
            int uid = intent.getIntExtra("uid" , 0);
            List<Diary> diaries = dao.queryBuilder().where(DiaryDao.Properties.Uid.eq(uid)).list();
            if(diaries.size() != 0){
                Diary diary = diaries.get(0);
                ed_title.setText(diary.title);
                ed_text.setText(diary.content);

            }else{
                Toast.makeText(this , "加载错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveDiary(){
        String title = ed_title.getText().toString().trim();
        if(title.equals("") || title == null){
            Toast.makeText(AddDiaryActivity.this , "标题不能为空" , Toast.LENGTH_SHORT).show();
            return;
        }
        Diary diary = new Diary();
        diary.content = ed_text.getText().toString().trim();
        diary.title = title;
        diary.createDate = getCurTime();
        diary.uid = UUID.randomUUID().toString();
        dao.insert(diary);
        Toast.makeText(this , "添加成功" , Toast.LENGTH_SHORT).show();
        finish();
    }

    private String getCurTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }



}
