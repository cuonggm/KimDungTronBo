package com.kimdung.kimdungtronbo.models;

/**
 * Created by Admin on 28/4/2017.
 */

public class Novel {
    private int mStId;
    private String mStName;
    private int mAuId;
    private String mStImage;
    private String mStDescribe;

    public Novel(int stId, String stName, int auId, String stImage, String stDescribe) {
        mStId = stId;
        mStName = stName;
        mAuId = auId;
        mStImage = stImage;
        mStDescribe = stDescribe;
    }

    public Novel() {
    }

    public int getStId() {
        return mStId;
    }

    public void setStId(int stId) {
        mStId = stId;
    }

    public String getStName() {
        return mStName;
    }

    public void setStName(String stName) {
        mStName = stName;
    }

    public int getAuId() {
        return mAuId;
    }

    public void setAuId(int auId) {
        mAuId = auId;
    }

    public String getStImage() {
        return mStImage;
    }

    public void setStImage(String stImage) {
        mStImage = stImage;
    }

    public String getStDescribe() {
        return mStDescribe;
    }

    public void setStDescribe(String stDescribe) {
        mStDescribe = stDescribe;
    }

    @Override
    public String toString() {
        return getStId() + "-" + getStName() + "-" + getAuId() + "-" + getStImage() + "-" + getStDescribe();
    }
}
