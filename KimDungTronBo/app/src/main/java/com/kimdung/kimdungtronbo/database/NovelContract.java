package com.kimdung.kimdungtronbo.database;

import android.provider.BaseColumns;

/**
 * Created by Admin on 25/4/2017.
 */

public class NovelContract {
    private NovelContract(){}

    public class NovelEntry  {
        public final static String TABLE_NAME = "kimdung";
        public final static String COLUMN_ST_ID = "stID";
        public final static String COLUMN_ST_NAME = "stName";
        public final static String COLUMN_AU_ID = "auID";
        public final static String COLUMN_ST_IMAGE = "stImage";
        public final static String COLUMN_ST_DESCRIBE = "stDescribe";
    }

    public class ChapterEntry {
        public final static  String TABLE_NAME = "st_kimdung";
        public final static  String COLUMN_DE_ID = "deID";
        public final static  String COLUMN_DE_NAME = "deName";
        public final static  String COLUMN_DE_SOURCE = "deSource";
        public final static  String COLUMN_DE_CONTENT = "decontent";
        public final static  String COLUMN_ST_ID = "stID";
        public final static  String COLUMN_DE_DATE = "deDate";
    }
}
