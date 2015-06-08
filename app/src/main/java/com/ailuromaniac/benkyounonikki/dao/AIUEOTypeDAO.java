package com.ailuromaniac.benkyounonikki.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ailuromaniac.benkyounonikki.database.DBHelper;
import com.ailuromaniac.benkyounonikki.data.AIUEOType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class AIUEOTypeDAO {
    public static final String TAG = "AIUEOTypeDAO";

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public AIUEOTypeDAO(Context context) {
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

    public List<AIUEOType> getAllAIUEOTypes() {
        List<AIUEOType> listAIUEOTypes = new ArrayList<AIUEOType>();

        Cursor cursor = database.query(DBHelper.TABLE_AIUEO_TYPES, DBHelper.allAIUEOTypeColumns(),
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AIUEOType aiueoType = cursorToAIUEOType(cursor);
                listAIUEOTypes.add(aiueoType);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listAIUEOTypes;
    }

    public AIUEOType getAIUEOTypeById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_AIUEO_TYPES, DBHelper.allAIUEOTypeColumns(),
                DBHelper.COLUMN_AIUEO_TYPE_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        AIUEOType aiueoType = cursorToAIUEOType(cursor);
        return aiueoType;
    }

    protected AIUEOType cursorToAIUEOType(Cursor cursor) {
        AIUEOType aiueoType = new AIUEOType(cursor.getLong(0),cursor.getString(1));
        return aiueoType;
    }
}
