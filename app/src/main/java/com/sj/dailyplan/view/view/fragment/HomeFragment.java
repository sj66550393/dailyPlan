package com.sj.dailyplan.view.view.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sj.dailyplan.R;
import com.sj.dailyplan.view.base.MyApplication;
import com.sj.dailyplan.view.mode.diary.DaoSession;
import com.sj.dailyplan.view.mode.diary.Diary;
import com.sj.dailyplan.view.mode.diary.DiaryDao;
import com.sj.dailyplan.view.utils.DiaryUtils;
import com.sj.dailyplan.view.view.AddDiaryActivity;

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
    private List<Diary> mDiaries;

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
        mDiaries = DiaryUtils.getDiaryList();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mRecyclerViewAdapter = new MyAdapter2(mDiaries);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new ItemDecoration());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "点击" , Toast.LENGTH_SHORT);
                AddDiaryActivity.previewDiary(getActivity() , mDiaries.get(position).getUid());
            }
        });

        mRecyclerViewAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "点击" , Toast.LENGTH_SHORT);
                showDeleteDialog(mDiaries.get(position).getUid());
                return true;
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRefreshData();

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

    private void showDeleteDialog(final String uid){
        final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getActivity());
        deleteDialog.setTitle("提示")
                .setMessage("确定删除吗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Diary diary = DiaryUtils.getDiaryByUid(uid);
                        if(!DiaryUtils.delete(diary)){
                            Toast.makeText(getActivity() , "删除失败", Toast.LENGTH_SHORT).show();
                        }else{
                            onRefreshData();
                            Toast.makeText(getActivity() , "删除成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private void onRefreshData(){
        mDiaries.clear();
        mRecyclerViewAdapter.notifyDataSetChanged();
        if(DiaryUtils.getDiaryList() != null){
            mDiaries.addAll(DiaryUtils.getDiaryList());
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }
}
