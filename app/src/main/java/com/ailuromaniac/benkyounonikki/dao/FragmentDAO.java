package com.ailuromaniac.benkyounonikki.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ailuromaniac.benkyounonikki.dataObject.Fragment;
import com.ailuromaniac.benkyounonikki.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aneesa on 6/29/2015.
 */
public class FragmentDao {

    public static final String TAG = "FragmentDao";

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private Context context;

    public FragmentDao(Context context) {
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

    public Fragment getFragmentById(long id) {
        Cursor cursor = database.query(DBHelper.TABLE_FRAGMENTS, DBHelper.allFragmentColumns(context.getResources()),
                DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            // make sure to close the cursor
            cursor.close();
        }

        Fragment fragment = cursorToFragment(cursor);
        return fragment;
    }

    public List<Fragment> getAllFragments() {
        List<Fragment> listFragments = new ArrayList<Fragment>();

        Cursor cursor = database.query(DBHelper.TABLE_FRAGMENTS, DBHelper.allFragmentColumns(context.getResources()),
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Fragment fragment = cursorToFragment(cursor);
                listFragments.add(fragment);
                cursor.moveToNext();
            }

            // make sure to close the cursor
            cursor.close();
        }
        return listFragments;
    }

    protected Fragment cursorToFragment(Cursor cursor) {
        Fragment fragment = new Fragment(cursor.getInt(0), cursor.getString(1));

        return fragment;
    }
}
