package com.ailuromaniac.benkyounonikki.database;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ailuromaniac.benkyounonikki.R;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";
    private Resources resources = null;

    // TODO: call tablenames from xml
    public static final String TABLE_FRAGMENTS = "table_fragments";
    public static final String TABLE_CONTENTS = "table_contents";

    public static final String COLUMN_ID = "_id";

    public DBHelper(Context context) {
        super(context, context.getResources().getString(R.string.database_name), null,
                Integer.parseInt(context.getResources().getString(R.string.database_version)));
        resources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // TODO: call tablenames from xml
        int[] tables = {    R.array.table_fragments ,
                            R.array.table_contents      };

        for(int i=0; i<tables.length; i++){
            String tableName;

            if (i==0) { tableName = TABLE_FRAGMENTS; }
            else { tableName = TABLE_CONTENTS; }

            String createTableSQL = "CREATE TABLE " + tableName + "(";

            String[] tableColumns = resources.getStringArray(tables[i]);

            try {
                for (int j=0; j<tableColumns.length; j++) {
                    JSONObject col = new JSONObject(tableColumns[j]);
                    // TODO: find a way to iterate JSON keys in order
//                    Iterator<String> colIter = col.keys();
//                    while (colIter.hasNext()) {
//                        createTableSQL += (col.get(colIter.next()) + " ");
//                    }


                    String colName = col.getString("column");
                    String colType = col.getString("type");
                    String extra = col.getString("extra");

                    createTableSQL += (colName + " " + colType + " " + extra);

                    if(j<tableColumns.length-1){
                        createTableSQL += ", ";
                    }else {
                        createTableSQL += ");";
                    }
                }

                database.execSQL(createTableSQL);
                Log.v(TAG, "Created table: " + createTableSQL);
            }catch (JSONException e) {
                Log.e(TAG,
                        "Error parsing JSON : " +e.toString());
            }
        }

        // populate the data after table creation
        this.populateFragmentsData(database);
        this.populateContentsData(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRAGMENTS);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, context.getResources().getString(R.string.database_name), factory,
                Integer.parseInt(context.getResources().getString(R.string.database_version)));
        resources = context.getResources();
    }

    // TODO: create a generic function to populate data for all tables
    private void populateFragmentsData(SQLiteDatabase database) {
        // TODO: call string array id from xml
        String insertSQL = "INSERT INTO " + TABLE_FRAGMENTS + " VALUES (";

        String[] rowData = resources.getStringArray(R.array.data_fragments);

        try {
            for (int j = 0; j < rowData.length; j++) {
                JSONObject row = new JSONObject(rowData[j]);
                String id = row.getString("_id");
                String name = row.getString("name");

                insertSQL += (id + ", \"" + name + "\");");

                database.execSQL(insertSQL);

                // reset the insert statement after executing the insert before
                insertSQL = "INSERT INTO " + TABLE_FRAGMENTS + " VALUES (";
            }

            Log.v(TAG, "Inserted data: " + TABLE_FRAGMENTS);
        } catch (JSONException e) {
            Log.e(TAG,
                    "Error parsing JSON : " + e.toString());
        }
    }

    private void populateContentsData(SQLiteDatabase database) {
        // TODO: call string array id from xml
        String insertSQL = "INSERT INTO " + TABLE_CONTENTS + " VALUES (";

        String[] rowData = resources.getStringArray(R.array.data_contents);

        try {
            for (int j = 0; j < rowData.length; j++) {
                JSONObject row = new JSONObject(rowData[j]);
                String id = row.getString("_id");
                String fragment_id = row.getString("fragment_id");
                String position = row.getString("position");
                String style = row.getString("style");
                String content = row.getString("content");

                insertSQL += (id + ", " + fragment_id + ", "+ position + ", \"" + style + "\", \"" +
                        content + "\");");

                database.execSQL(insertSQL);

                // reset the insert statement after executing the insert before
                insertSQL = "INSERT INTO " + TABLE_CONTENTS + " VALUES (";
            }

            Log.v(TAG, "Inserted data: " + TABLE_CONTENTS);
        } catch (JSONException e) {
            Log.e(TAG,
                    "Error parsing JSON : " + e.toString());
        }
    }

    public static String[] allFragmentColumns(Resources resources){
        String[] tableColumns = resources.getStringArray(R.array.table_fragments);

        try {
            for (int j = 0; j < tableColumns.length; j++) {
                JSONObject col = new JSONObject(tableColumns[j]);
                tableColumns[j] = col.getString("column");
            }
        }catch (JSONException e) {
                Log.e(TAG,
                        "Error parsing JSON : " + e.toString());
        }
        return tableColumns;
    }

    public static String[] allContentColumns(Resources resources){
        String[] tableColumns = resources.getStringArray(R.array.table_contents);

        try {
            for (int j = 0; j < tableColumns.length; j++) {
                JSONObject col = new JSONObject(tableColumns[j]);
                tableColumns[j] = col.getString("column");
            }
        }catch (JSONException e) {
            Log.e(TAG,
                    "Error parsing JSON : " + e.toString());
        }
        return tableColumns;
    }
}