package com.sj.dailyplan.view.utils;


import com.sj.dailyplan.view.base.MyApplication;
import com.sj.dailyplan.view.mode.diary.DaoSession;
import com.sj.dailyplan.view.mode.diary.Diary;
import com.sj.dailyplan.view.mode.diary.DiaryDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class DiaryUtils {


    public static List<Diary> getDiaryList(){
        List<Diary> list = new ArrayList<>();
        DaoSession dao = MyApplication.INSTANCE.getDaoSesiion();
        list.addAll(dao.getDiaryDao().loadAll());
        Collections.reverse(list);
        return list;
    }

    public static boolean addDiary(Diary diary){
        boolean isSuccess = false;
        DaoSession dao = MyApplication.INSTANCE.getDaoSesiion();
        DiaryDao diaryDao = dao.getDiaryDao();
        List<Diary> list = diaryDao.queryBuilder().where(DiaryDao.Properties.Uid.eq(diary.getUid())).list();
        if(list != null || list.size() == 0) {
            diaryDao.insert(diary);
            isSuccess = true;
        }
        return isSuccess;
    }

    public static Diary getDiaryByUid(String uid){
        DaoSession daoSession = MyApplication.INSTANCE.getDaoSesiion();
        DiaryDao diaryDao = daoSession.getDiaryDao();
        List<Diary> diaries = diaryDao.queryBuilder().where(DiaryDao.Properties.Uid.eq(uid)).list();
        if(diaries != null){
            return diaries.get(0);
        }else{
            return null;
        }
    }

    public static boolean delete(Diary diary){
        DaoSession daoSession = MyApplication.INSTANCE.getDaoSesiion();
        DiaryDao diaryDao = daoSession.getDiaryDao();
        diaryDao.delete(diary);
        List<Diary> list = diaryDao.queryBuilder().where(DiaryDao.Properties.Uid.eq(diary.getUid())).list();
        if(list != null || list.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean update(Diary diary){
        DaoSession daoSession = MyApplication.INSTANCE.getDaoSesiion();
        DiaryDao diaryDao = daoSession.getDiaryDao();
        diaryDao.update(diary);
        return true;
    }

    public static List<Diary> queryByTitle(String title){
        DaoSession daoSession = MyApplication.INSTANCE.getDaoSesiion();
        DiaryDao diaryDao = daoSession.getDiaryDao();
        return diaryDao.queryBuilder().where(DiaryDao.Properties.Title.like("%"+title+"%")).orderAsc(DiaryDao.Properties.CreateDate).list();
    }
}
