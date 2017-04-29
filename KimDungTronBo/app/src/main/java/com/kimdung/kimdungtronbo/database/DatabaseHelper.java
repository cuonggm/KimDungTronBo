package com.kimdung.kimdungtronbo.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import com.kimdung.kimdungtronbo.models.Chapter;
import com.kimdung.kimdungtronbo.models.Novel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 25/4/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "kimdung.sqlite";
    public final static int DB_VERSION = 1;
    public final static String PACKAGE_NAME = "com.kimdung.kimdungtronbo";
    private static final String TAG = "DatabaseHelper";

    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static boolean checkExistDatabase(Context context) {
        return context.getDatabasePath(DB_NAME).exists();
    }

    public static void copyDataBase(Context context) throws IOException {
        // kiem tra database ton tai chua
        if (context.getDatabasePath(DB_NAME).exists()) {
            return;
        }
        // tao thu muc chua databases
        File file = new File("/data/data/" + PACKAGE_NAME + "/databases");
        if (!file.exists())
            file.mkdir();
        // khoi tao 1 so bien lien quan
        File dbPath = context.getDatabasePath(DB_NAME);
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // open input from asset
        InputStream myInput = null;
        myInput = context.getAssets().open(DB_NAME);
        // copy tu input sang output
        myOutput = new FileOutputStream(dbPath);
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // close stream
        myOutput.close();
        myOutput.flush();
        myInput.close();
    }

    public String getStNameOfChapter(int stId) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = NovelContract.NovelEntry.COLUMN_ST_ID + " = ?";
        String[] selectionArgs = {stId + ""};
        String[] columns = {NovelContract.NovelEntry.COLUMN_ST_NAME};
        Cursor c = db.query(NovelContract.NovelEntry.TABLE_NAME, columns, selection, selectionArgs,
                null,
                null,
                null);
        db.close();
        if (c != null && c.moveToFirst()) {
            String stName = c.getString(c.getColumnIndex(NovelContract.NovelEntry.COLUMN_ST_NAME));
            return stName;
        }
        return "";
    }

    public List<Novel> getNovelList() {
        List<Novel> list = new ArrayList<>();

        String[] columns = {NovelContract.NovelEntry.COLUMN_ST_ID,
                NovelContract.NovelEntry.COLUMN_ST_NAME,
                NovelContract.NovelEntry.COLUMN_AU_ID,
                NovelContract.NovelEntry.COLUMN_ST_IMAGE,
                NovelContract.NovelEntry.COLUMN_ST_DESCRIBE};

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(NovelContract.NovelEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (c != null && c.moveToFirst()) {
            do {
                int stId = c.getInt(c.getColumnIndex(NovelContract.NovelEntry.COLUMN_ST_ID));
                String stName = c.getString(c.getColumnIndex(NovelContract.NovelEntry.COLUMN_ST_NAME));
                int auId = c.getInt(c.getColumnIndex(NovelContract.NovelEntry.COLUMN_AU_ID));
                String stImage = c.getString(c.getColumnIndex(NovelContract.NovelEntry.COLUMN_ST_IMAGE));
                String stDescribe = c.getString(c.getColumnIndex(NovelContract.NovelEntry.COLUMN_ST_DESCRIBE));

                Novel novel = new Novel(stId, stName, auId, stImage, stDescribe);
                list.add(novel);
            } while (c.moveToNext());
        }
        db.close();
        return list;
    }

    public List<Chapter> getAllChapter() {
        List<Chapter> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {NovelContract.ChapterEntry.COLUMN_DE_ID,
                NovelContract.ChapterEntry.COLUMN_DE_NAME,
                NovelContract.ChapterEntry.COLUMN_DE_SOURCE,
                NovelContract.ChapterEntry.COLUMN_DE_CONTENT,
                NovelContract.ChapterEntry.COLUMN_ST_ID,
                NovelContract.ChapterEntry.COLUMN_DE_DATE};
        Cursor c = db.query(NovelContract.ChapterEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (c != null && c.moveToFirst()) {
            do {
                int deId = c.getInt(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_ID));
                String deName = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_NAME));
                String deSource = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_SOURCE));
                String deContent = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_CONTENT));
                int stId = c.getInt(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_ST_ID));
                String stringDeDate = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_DATE));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date deDate=null;
                try {
                    deDate = format.parse(stringDeDate);
                } catch (ParseException e) {
                }

                Chapter chapter = new Chapter(deId, deName, deSource, deContent, stId, deDate);
                list.add(chapter);
            } while (c.moveToNext());
        }
        db.close();
        return list;
    }

    public List<Chapter> getChaptersOfANovel(int stId) {
        List<Chapter> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = NovelContract.ChapterEntry.COLUMN_ST_ID + " = ?";
        String[] selectionArgs = {stId+""};
        String[] columns = {NovelContract.ChapterEntry.COLUMN_DE_ID,
                NovelContract.ChapterEntry.COLUMN_DE_NAME,
                NovelContract.ChapterEntry.COLUMN_DE_SOURCE,
                NovelContract.ChapterEntry.COLUMN_DE_CONTENT,
                NovelContract.ChapterEntry.COLUMN_ST_ID,
                NovelContract.ChapterEntry.COLUMN_DE_DATE};
        Cursor c = db.query(NovelContract.ChapterEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (c != null && c.moveToFirst()) {
            do {
                int deId = c.getInt(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_ID));
                String deName = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_NAME));
                String deSource = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_SOURCE));
                String deContent = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_CONTENT));
                int stID = c.getInt(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_ST_ID));
                String stringDeDate = c.getString(c.getColumnIndex(NovelContract.ChapterEntry.COLUMN_DE_DATE));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Date deDate=null;
                try {
                    deDate = format.parse(stringDeDate);
                } catch (ParseException e) {
                }

                Chapter chapter = new Chapter(deId, deName, deSource, deContent, stID, deDate);
                list.add(chapter);
            } while (c.moveToNext());
        }
        db.close();
        return list;
    }
}
