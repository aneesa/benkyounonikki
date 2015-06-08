package com.ailuromaniac.benkyounonikki.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ailuromaniac.benkyounonikki.database.DBHelper;
import com.ailuromaniac.benkyounonikki.data.AIUEOGroup;
import com.ailuromaniac.benkyounonikki.data.AIUEORow;
import com.ailuromaniac.benkyounonikki.data.AIUEOType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class AIUEORowDAO {
    public static final String TAG = "AIUEORowDAO";

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public AIUEORowDAO(Context context) {
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

    public List<AIUEORow> getAllAIUEORows() {
        List<AIUEORow> listAIUEORows = new ArrayList<AIUEORow>();

        Cursor cursor = database.query(DBHelper.TABLE_AIUEO_ROWS, DBHelper.allAIUEORowColumns(),
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AIUEORow aiueoRow = cursorToAIUEORow(cursor);
                listAIUEORows.add(aiueoRow);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listAIUEORows;
    }

    public AIUEORow getAIUEORowById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_AIUEO_ROWS, DBHelper.allAIUEORowColumns(),
                DBHelper.COLUMN_AIUEO_ROW_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        AIUEORow aiueoRow = cursorToAIUEORow(cursor);
        return aiueoRow;
    }

    protected AIUEORow cursorToAIUEORow(Cursor cursor) {
        AIUEOGroupDAO aiueoGroupDAO = new AIUEOGroupDAO(context);
        AIUEOGroup aiueoGroup = aiueoGroupDAO.getAIUEOGroupById(cursor.getLong(1));

        AIUEOTypeDAO aiueoTypeDAO = new AIUEOTypeDAO(context);
        AIUEOType aiueoType = aiueoTypeDAO.getAIUEOTypeById(cursor.getLong(2));

        AIUEORow aiueoRow = new AIUEORow(cursor.getLong(0),aiueoGroup, aiueoType, cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

        aiueoGroupDAO.close();
        aiueoTypeDAO.close();

        return aiueoRow;
    }
}
