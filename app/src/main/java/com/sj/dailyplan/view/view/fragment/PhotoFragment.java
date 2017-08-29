package com.sj.dailyplan.view.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.sj.dailyplan.R;


/**
 * Created by Administrator on 2017/7/4.
 */

public class PhotoFragment extends Fragment {
    private Toolbar mToolBar;
    private TextView mTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_photo , container , false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextView = (TextView) view.findViewById(R.id.tv_title);
        mTextView.setText("趣图");
        super.onViewCreated(view, savedInstanceState);
    }
}
