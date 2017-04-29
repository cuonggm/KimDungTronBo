package com.kimdung.kimdungtronbo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends Fragment implements ChapterListFragment.OnChangeContentReading {

    private static final String TAG = "ReadingFragment";
    private TextView mTextViewContent;

    public ReadingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reading, container, false);
        mTextViewContent = (TextView) v.findViewById(R.id.text_view_reading_content);
        return v;
    }

    public static ReadingFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ReadingFragment fragment = new ReadingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void refresh(String content) {
        mTextViewContent.setText(Html.fromHtml(content).toString());
    }
}
