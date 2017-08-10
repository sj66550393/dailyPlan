package com.sj.dailyplan.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sj.diary.R;
import com.sj.diary.view.view.fragment.FindFragment;
import com.sj.diary.view.view.fragment.HomeFragment;
import com.sj.diary.view.view.fragment.PersonalFragment;
import com.sj.diary.view.view.fragment.PhotoFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FragmentManager fm;
    private Fragment mHomeFragment;
    private Fragment mFindFragment;
    private Fragment mPhotoFragment;
    private Fragment mPersonalFragment;
    private BottomNavigationView mBottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    switchFragment(0);
                    return true;
                case R.id.navigation_dashboard:
                    switchFragment(1);
                    return true;
                case R.id.navigation_photo:
                    switchFragment(2);
                    return true;
                case R.id.navigation_notifications:
                    switchFragment(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
        switchFragment(0);
    }


    @SuppressLint("ResourceType")
    private void switchFragment(int index){
        if(fm == null){
            fm = getSupportFragmentManager();
        }
        FragmentTransaction transaction = fm.beginTransaction();
        if(mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            transaction.add(R.id.frag_main, mHomeFragment);
        }

        if(mFindFragment == null) {
            mFindFragment = new FindFragment();
            transaction.add(R.id.frag_main, mFindFragment);
        }

        if(mPhotoFragment == null) {
            mPhotoFragment = new PhotoFragment();
            transaction.add(R.id.frag_main, mPhotoFragment);
        }

        if(mPersonalFragment == null) {
            mPersonalFragment = new PersonalFragment();
            transaction.add(R.id.frag_main, mPersonalFragment);
        }

        switch (index){
            case 0:
                transaction.hide(mFindFragment).hide(mPersonalFragment).hide(mPhotoFragment).show(mHomeFragment);
                break;
            case 1:
                transaction.hide(mHomeFragment).hide(mPersonalFragment).hide(mPhotoFragment).show(mFindFragment);
                break;
            case 2:
                transaction.hide(mFindFragment).hide(mPersonalFragment).hide(mHomeFragment).show(mPhotoFragment);
                break;
            case 3:
                transaction.hide(mFindFragment).hide(mHomeFragment).hide(mPhotoFragment).show(mPersonalFragment);
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        switchFragment(0);
        super.onNewIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG , "onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }
}
