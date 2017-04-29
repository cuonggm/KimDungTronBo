package com.kimdung.kimdungtronbo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kimdung.kimdungtronbo.adapters.ViewPagerReadingAdapter;
import com.kimdung.kimdungtronbo.database.DatabaseHelper;
import com.kimdung.kimdungtronbo.models.Chapter;

import java.util.List;

public class ReadingActivity extends AppCompatActivity {

    public final static String EXTRA_ST_ID = "st_id";
    public final static String EXTRA_ST_NAME = "st_name";
    private static final String TAG = "ReadingActivity";

    private int mStId;
    private String mStName;
    private List<Chapter> mChapterList;

    private ViewPager mViewPagerReading;
    private TabLayout mTabLayoutReading;

    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        mDatabaseHelper = new DatabaseHelper(this);

        // lay ra stId
        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                mStId = bundle.getInt(EXTRA_ST_ID);
                mStName = bundle.getString(EXTRA_ST_NAME);
                setTitle(mStName);
            }
        }

        // lay tham chieu tablayout va view pager
        mViewPagerReading = (ViewPager) findViewById(R.id.view_pager_reading);
        mTabLayoutReading = (TabLayout) findViewById(R.id.tab_layout_reading);

        // do du lieu vao view pager
        ViewPagerReadingAdapter adapter = new ViewPagerReadingAdapter(getSupportFragmentManager(), mStId, mViewPagerReading);
        mViewPagerReading.setAdapter(adapter);

        // setup tablayout
        mTabLayoutReading.setupWithViewPager(mViewPagerReading);
    }
}
