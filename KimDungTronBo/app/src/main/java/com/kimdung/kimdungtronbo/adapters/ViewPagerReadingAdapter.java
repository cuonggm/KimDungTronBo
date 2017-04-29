package com.kimdung.kimdungtronbo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.kimdung.kimdungtronbo.ChapterListFragment;
import com.kimdung.kimdungtronbo.ReadingFragment;

/**
 * Created by Admin on 28/4/2017.
 */

public class ViewPagerReadingAdapter extends FragmentPagerAdapter implements ChapterListFragment.OnJumpToReadingFragment {

    private static final String TAG = "ViewPagerReadingAdapter";
    private String[] mTitles = {"MỤC LỤC", "ĐỌC TRUYỆN"};
    private int mStId;

    ChapterListFragment mChapterListFragment;
    ReadingFragment mReadingFragment;
    ViewPager mViewPager;

    public ViewPagerReadingAdapter(FragmentManager fm, int stId, ViewPager viewPager) {
        super(fm);
        mStId = stId;
        mViewPager = viewPager;

        mChapterListFragment = ChapterListFragment.newInstance(mStId);
        mReadingFragment = ReadingFragment.newInstance();
        mChapterListFragment.setOnChangeContentReading(mReadingFragment);
        mChapterListFragment.setOnJumpToReadingFragment(this);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mChapterListFragment;
            case 1:
                return mReadingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    public void changeToReadingFragment() {
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void jump() {
        changeToReadingFragment();
    }
}
