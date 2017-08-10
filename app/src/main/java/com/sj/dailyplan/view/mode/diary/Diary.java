package com.sj.dailyplan.view.mode.diary;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/7/10.
 */

@Entity
public class Diary {
    @Id
    public String uid;
    public String title;
    public String content;
    public String createDate;
    @Generated(hash = 521700532)
    public Diary(String uid, String title, String content, String createDate) {
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }
    @Generated(hash = 112123061)
    public Diary() {
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
