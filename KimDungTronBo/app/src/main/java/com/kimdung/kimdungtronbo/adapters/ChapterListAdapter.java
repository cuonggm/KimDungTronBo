package com.kimdung.kimdungtronbo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kimdung.kimdungtronbo.ChapterListFragment;
import com.kimdung.kimdungtronbo.R;
import com.kimdung.kimdungtronbo.models.Chapter;

import java.util.List;

/**
 * Created by Admin on 28/4/2017.
 */

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ViewHolder> {

    List<Chapter> mChapterList;
    ChapterListFragment.OnChangeContentReading mOnChangeContentReading;
    ChapterListFragment.OnJumpToReadingFragment mOnJumpToReadingFragment;

    public void setOnChangeContentReading(ChapterListFragment.OnChangeContentReading onChangeContentReading) {
        mOnChangeContentReading = onChangeContentReading;
    }

    public void setOnJumpToReadingFragment(ChapterListFragment.OnJumpToReadingFragment onJumpToReadingFragment) {
        mOnJumpToReadingFragment = onJumpToReadingFragment;
    }

    public ChapterListAdapter(List<Chapter> chapterList, ChapterListFragment.OnChangeContentReading onChangeContentReading) {
        mChapterList = chapterList;
        mOnChangeContentReading = onChangeContentReading;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_chapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chapter chapter = mChapterList.get(position);
        holder.bindData(chapter);
    }

    @Override
    public int getItemCount() {
        return mChapterList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewDeName;
        private TextView mTextViewDeDate;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewDeName = (TextView) itemView.findViewById(R.id.text_view_dename);
            mTextViewDeDate = (TextView) itemView.findViewById(R.id.text_view_dedate);
        }

        public void bindData(final Chapter chapter) {
            mTextViewDeName.setText(chapter.getDeName());
            mTextViewDeDate.setText(chapter.getStringDeDate());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnChangeContentReading != null)
                        mOnChangeContentReading.refresh(chapter.getDeContent());
                    if (mOnJumpToReadingFragment != null)
                        mOnJumpToReadingFragment.jump();
                }
            });
        }
    }
}
