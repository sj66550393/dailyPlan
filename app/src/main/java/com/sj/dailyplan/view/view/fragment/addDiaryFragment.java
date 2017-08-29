package com.sj.dailyplan.view.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sj.dailyplan.R;


/**
 * Created by Administrator on 2017/8/14.
 */

public class addDiaryFragment extends Fragment {

    private EditText ed_title;
    private EditText ed_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_adddiary , container , false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ed_content = (EditText) view.findViewById(R.id.ed_content);
        ed_title = (EditText) view.findViewById(R.id.ed_title);
    }
}
