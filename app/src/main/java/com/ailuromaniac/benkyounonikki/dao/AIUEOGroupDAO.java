package com.ailuromaniac.benkyounonikki.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ailuromaniac.benkyounonikki.database.DBHelper;
import com.ailuromaniac.benkyounonikki.data.AIUEOGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class AIUEOGroupDAO {
    public static final String TAG = "AIUEOGroupDAO";

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public AIUEOGroupDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on openning database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<AIUEOGroup> getAllAIUEOGroups() {
        List<AIUEOGroup> listAIUEOGroups = new ArrayList<AIUEOGroup>();

        Cursor cursor = database.query(DBHelper.TABLE_AIUEO_GROUPS, DBHelper.allAIUEOGroupColumns(),
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AIUEOGroup aiueoGroup = cursorToAIUEOGroup(cursor);
                listAIUEOGroups.add(aiueoGroup);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listAIUEOGroups;
    }

    public AIUEOGroup getAIUEOGroupById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_AIUEO_GROUPS, DBHelper.allAIUEOGroupColumns(),
                DBHelper.COLUMN_AIUEO_GROUP_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        AIUEOGroup aiueoGroup = cursorToAIUEOGroup(cursor);
        return aiueoGroup;
    }

    protected AIUEOGroup cursorToAIUEOGroup(Cursor cursor) {
        AIUEOGroup aiueoGroup = new AIUEOGroup(cursor.getLong(0),cursor.getString(1));
        return aiueoGroup;
    }
}
