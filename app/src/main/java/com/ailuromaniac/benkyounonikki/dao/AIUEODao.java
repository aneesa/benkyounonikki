package com.ailuromaniac.benkyounonikki.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ailuromaniac.benkyounonikki.database.DBHelper;
import com.ailuromaniac.benkyounonikki.data.AIUEO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesa on 6/8/2015.
 */
public class AIUEODao {

    public static final String TAG = "AIUEODao";

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public AIUEODao(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        // open the database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public AIUEO getAIUEOById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_AIUEOS, DBHelper.allAIUEOColumns(),
                DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            // make sure to close the cursor
            cursor.close();
        }

        AIUEO aiueo = cursorToAIUEO(cursor);
        return aiueo;
    }

    public List<AIUEO> getAllAIUEOs() {
        List<AIUEO> listAIUEOs = new ArrayList<AIUEO>();

        Cursor cursor = database.query(DBHelper.TABLE_AIUEOS, DBHelper.allAIUEOColumns(),
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AIUEO aiueo = cursorToAIUEO(cursor);
                listAIUEOs.add(aiueo);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listAIUEOs;
    }

    protected AIUEO cursorToAIUEO(Cursor cursor) {
        AIUEO aiueo = new AIUEO(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                                cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));

        return aiueo;
    }
}