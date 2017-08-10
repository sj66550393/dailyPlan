package com.sj.dailyplan.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sj.diary.R;
import com.sj.diary.base.MyApplication;
import com.sj.diary.mode.diary.DaoSession;
import com.sj.diary.mode.diary.Diary;
import com.sj.diary.mode.diary.DiaryDao;
import com.sj.diary.view.view.AddDiaryActivity;

import java.util.List;


/**
 * Created by Administrator on 2017/7/4.
 */

public class HomeFragment extends Fragment {

    private Toolbar mToolBar;
    private AppCompatActivity mActivity;
    private RecyclerView mRecyclerView;
    private static final String TAG = "HomeFragment";
    private DaoSession mDaoSession;
    private DiaryDao mDiaryDao;
    private MyAdapter2 mRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home , container , false);
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        mDaoSession = myApplication.getDaoSesiion();
        mDiaryDao = mDaoSession.getDiaryDao();
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolBar.setTitle("我的日记");
        mToolBar.setLogo(R.drawable.ic_diary_title);
        setHasOptionsMenu(true);
        mActivity.setSupportActionBar(mToolBar);
        mRecyclerViewAdapter = new MyAdapter2(mDiaryDao.queryBuilder()
                .orderDesc(DiaryDao.Properties.CreateDate).list());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new ItemDecoration());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu , menu);
        super.onCreateOptionsMenu(menu , inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG , "item = " + item.getItemId());
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(getActivity() , AddDiaryActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (AppCompatActivity) context;
        super.onAttach(context);
    }

/*
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        List<Diary> diaryList = new ArrayList<>();


        public MyAdapter() {
            diaryList = mDiaryDao.queryBuilder()
            .orderDesc(DiaryDao.Properties.CreateDate).list();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //    View view = View.inflate(getActivity(),R.layout.item_diary , null );
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_diary , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Diary diary = diaryList.get(position);
            holder.tv_title.setText(diary.title);
            holder.tv_date.setText(diary.createDate);
            holder.tv_context.setText(diary.content);
        }


        @Override
        public int getItemCount() {
            return diaryList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView tv_title;
            private TextView tv_context;
            private TextView tv_date;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_context = (TextView) itemView.findViewById(R.id.tv_context);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_date = (TextView) itemView.findViewById(R.id.tv_date);

            }
        }
    }
*/


    class MyAdapter2 extends BaseQuickAdapter<Diary , BaseViewHolder> {


        public MyAdapter2(@Nullable List<Diary> data) {
            super(R.layout.item_diary, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Diary item) {
            helper.setText(R.id.tv_context , item.content);
            helper.setText(R.id.tv_title , item.title);
            helper.setText(R.id.tv_date , item.createDate);
        }
    }



    class ItemDecoration extends RecyclerView.ItemDecoration{

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(20,0,20,10);
        }
    }
}
