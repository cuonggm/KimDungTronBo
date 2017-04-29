package com.kimdung.kimdungtronbo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kimdung.kimdungtronbo.R;
import com.kimdung.kimdungtronbo.ReadingActivity;
import com.kimdung.kimdungtronbo.models.Novel;

import java.util.List;

/**
 * Created by Admin on 28/4/2017.
 */

public class NovelListAdapter extends RecyclerView.Adapter<NovelListAdapter.ViewHolder> {

    private List<Novel> mNovelList;
    Context mContext;

    public NovelListAdapter(List<Novel> novelList, Context context) {
        mNovelList = novelList;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_novel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Novel novel = mNovelList.get(position);
        holder.bindData(novel);
    }

    @Override
    public int getItemCount() {
        return mNovelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewStName;
        private TextView mTextViewStDescribe;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewStName = (TextView) itemView.findViewById(R.id.text_view_stname);
            mTextViewStDescribe = (TextView) itemView.findViewById(R.id.text_view_stdescribe);
        }

        public void bindData(final Novel novel) {
            mTextViewStName.setText(novel.getStName());
            mTextViewStDescribe.setText(Html.fromHtml(novel.getStDescribe()).toString());
            // set su kien click vao 1 tieu thuyet
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, ReadingActivity.class);
                    i.putExtra(ReadingActivity.EXTRA_ST_ID, novel.getStId());
                    i.putExtra(ReadingActivity.EXTRA_ST_NAME, novel.getStName());
                    mContext.startActivity(i);
                }
            });
        }
    }
}
