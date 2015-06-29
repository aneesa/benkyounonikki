package com.ailuromaniac.benkyounonikki.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ailuromaniac.benkyounonikki.dataObject.Content;
import com.ailuromaniac.benkyounonikki.dataObject.Fragment;
import com.ailuromaniac.benkyounonikki.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class ContentDao {

    public static final String TAG = "FragmentDao";

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public ContentDao(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        // open the database
        try {
            database = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void close() {
        dbHelper.close();
    }

    public Content getContentById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_CONTENTS, DBHelper.allContentColumns(context.getResources()),
                DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            // make sure to close the cursor
            cursor.close();
        }

        Content content = cursorToContent(cursor);
        return content;
    }

    public List<Content> getContentByFragmentId(int fragmentId) {
        List<Content> listContents = new ArrayList<Content>();

        Cursor cursor = database.query(DBHelper.TABLE_CONTENTS, DBHelper.allContentColumns(context.getResources()),
                "fragment_id = ?",
                new String[]{String.valueOf(fragmentId)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Content content = cursorToContent(cursor);
                listContents.add(content);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listContents;
    }

    public List<Content> getAllContents() {
        List<Content> listContents = new ArrayList<Content>();

        Cursor cursor = database.query(DBHelper.TABLE_CONTENTS, DBHelper.allContentColumns(context.getResources()),
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Content content = cursorToContent(cursor);
                listContents.add(content);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listContents;
    }

    protected Content cursorToContent(Cursor cursor) {

        Content content = new Content(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                                        cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return content;
    }
}
