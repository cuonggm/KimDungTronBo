package com.kimdung.kimdungtronbo.models;

import android.content.Context;

import com.kimdung.kimdungtronbo.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Admin on 28/4/2017.
 */

public class Chapter {
    private int mDeId;
    private String mDeName;
    private String mDeSource;
    private String mDeContent;
    private int mStId;
    private Date mDeDate;

    public Chapter(int deId, String deName, String deSource, String deContent, int stId, Date deDate) {
        mDeId = deId;
        mDeName = deName;
        mDeSource = deSource;
        mDeContent = deContent;
        mStId = stId;
        mDeDate = deDate;
    }

    public Chapter() {
    }

    public int getDeId() {
        return mDeId;
    }

    public void setDeId(int deId) {
        mDeId = deId;
    }

    public String getDeName() {
        return mDeName;
    }

    public void setDeName(String deName) {
        mDeName = deName;
    }

    public String getDeSource() {
        return mDeSource;
    }

    public void setDeSource(String deSource) {
        mDeSource = deSource;
    }

    public String getDeContent() {
        return mDeContent;
    }

    public void setDeContent(String deContent) {
        mDeContent = deContent;
    }

    public int getStId() {
        return mStId;
    }

    public void setStId(int stId) {
        mStId = stId;
    }

    public Date getDeDate() {
        return mDeDate;
    }

    public void setDeDate(Date deDate) {
        mDeDate = deDate;
    }

    public String getStringDeDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(getDeDate());
    }

    public String getStName(Context context) {
        DatabaseHelper helper = new DatabaseHelper(context);
        return helper.getStNameOfChapter(mStId);
    }


    @Override
    public String toString() {
        return getDeId()
//                + "-" + getDeName()
//                + "-" + getDeSource()
//                + "-" + getDeContent()
                + "-" + getStId()
                + "-" + getStringDeDate();
    }
}
