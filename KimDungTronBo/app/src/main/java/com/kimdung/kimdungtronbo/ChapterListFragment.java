package com.kimdung.kimdungtronbo;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kimdung.kimdungtronbo.adapters.ChapterListAdapter;
import com.kimdung.kimdungtronbo.database.DatabaseHelper;
import com.kimdung.kimdungtronbo.models.Chapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterListFragment extends Fragment {

    public final static String EXTRA_ST_ID = "stId";
    private static final String TAG = "ChapterListFragment";

    private RecyclerView mRecyclerViewChapterList;
    private DatabaseHelper mDatabaseHelper;
    private List<Chapter> mChapterList;
    private ChapterListAdapter mAdapter;

    private int mStId;

    OnChangeContentReading mOnChangeContentReading;
    OnJumpToReadingFragment mOnJumpToReadingFragment;

    public void setOnChangeContentReading(OnChangeContentReading onChangeContentReading) {
        mOnChangeContentReading = onChangeContentReading;
    }

    public void setOnJumpToReadingFragment(OnJumpToReadingFragment onJumpToReadingFragment) {
        mOnJumpToReadingFragment = onJumpToReadingFragment;
    }

    public ChapterListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDatabaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStId = getArguments().getInt(EXTRA_ST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chapter_list, container, false);
        // lay tham chieu recycler view chapter list
        mRecyclerViewChapterList = (RecyclerView) v.findViewById(R.id.recycler_view_chapter_list);
        mRecyclerViewChapterList.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        // do du lieu vao adapter
        mChapterList = mDatabaseHelper.getChaptersOfANovel(mStId);
        mAdapter = new ChapterListAdapter(mChapterList, mOnChangeContentReading);
        mAdapter.setOnJumpToReadingFragment(mOnJumpToReadingFragment);
        mRecyclerViewChapterList.setAdapter(mAdapter);
    }

    public static ChapterListFragment newInstance(int stId) {
        
        Bundle args = new Bundle();
        args.putInt(EXTRA_ST_ID, stId);

        ChapterListFragment fragment = new ChapterListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnChangeContentReading {
        public void refresh(String content);
    }

    public interface OnJumpToReadingFragment {
        public void jump();
    }
}
