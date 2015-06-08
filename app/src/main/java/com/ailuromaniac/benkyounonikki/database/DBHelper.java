package com.ailuromaniac.benkyounonikki.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aneesa on 6/6/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";

    // columns of the  groups
    public static final String TABLE_AIUEO_GROUPS = "aiueo_groups";
    public static final String COLUMN_AIUEO_GROUP_ID = "_id";
    public static final String COLUMN_AIUEO_GROUP = "aiueo_group";

    // columns of the character's types
    public static final String TABLE_AIUEO_TYPES = "aiueo_types";
    public static final String COLUMN_AIUEO_TYPE_ID = "_id";
    public static final String COLUMN_AIUEO_TYPE = "aiueo_type";

    // columns of the characters
    public static final String TABLE_AIUEO_ROWS = "aiueo_rows";
    public static final String COLUMN_AIUEO_ROW_ID = "_id";
    public static final String COLUMN_AIUEO_ROW_GROUP_ID = "aiueo_group_id";
    public static final String COLUMN_AIUEO_ROW_TYPE_ID = "aiueo_type_id";
    public static final String COLUMN_VOWEL_A = "aiueo_vowel_a";
    public static final String COLUMN_VOWEL_I = "aiueo_vowel_i";
    public static final String COLUMN_VOWEL_U = "aiueo_vowel_u";
    public static final String COLUMN_VOWEL_E = "aiueo_vowel_e";
    public static final String COLUMN_VOWEL_O = "aiueo_vowel_o";

    private static final String DATABASE_NAME = "benkyounonikki.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statements for table creations
    private static final String SQL_CREATE_TABLE_AIUEO_GROUPS = "CREATE TABLE " + TABLE_AIUEO_GROUPS + "("
            + COLUMN_AIUEO_GROUP_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_AIUEO_GROUP + " TEXT NOT NULL "
            +");";

    private static final String SQL_CREATE_TABLE_AIUEO_TYPES = "CREATE TABLE " + TABLE_AIUEO_TYPES + "("
            + COLUMN_AIUEO_TYPE_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_AIUEO_TYPE + " TEXT NOT NULL "
            +");";

    // SQL statement of the employees table creation
    private static final String SQL_CREATE_TABLE_AIUEO_ROWS = "CREATE TABLE " + TABLE_AIUEO_ROWS + "("
            + COLUMN_AIUEO_ROW_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_AIUEO_ROW_GROUP_ID + " INTEGER NOT NULL, "
            + COLUMN_AIUEO_ROW_TYPE_ID + " INTEGER NOT NULL, "
            + COLUMN_VOWEL_A + " TEXT, "
            + COLUMN_VOWEL_I + " TEXT, "
            + COLUMN_VOWEL_U + " TEXT, "
            + COLUMN_VOWEL_E + " TEXT, "
            + COLUMN_VOWEL_O + " TEXT "
            +");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_AIUEO_GROUPS);
        Log.v(TAG, "Created table " + TABLE_AIUEO_GROUPS);

        database.execSQL(SQL_CREATE_TABLE_AIUEO_TYPES);
        Log.v(TAG, "Created table " + TABLE_AIUEO_TYPES);

        database.execSQL(SQL_CREATE_TABLE_AIUEO_ROWS);
        Log.v(TAG, "Created table " + TABLE_AIUEO_ROWS);

        populateStaticData(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIUEO_ROWS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIUEO_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIUEO_GROUPS);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    private void populateStaticData(SQLiteDatabase database) {
        // insert into table aiueo_groups
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (0, \"a\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (1, \"ka\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (2, \"ga\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (3, \"sa\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (4, \"za\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (5, \"ta\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (6, \"da\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (7, \"na\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (8, \"ha\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (9, \"ba\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (10, \"pa\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (11, \"ma\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (12, \"ya\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (13, \"ra\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (14, \"wa\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_GROUPS + " VALUES (15, \"n\")");
        Log.v(TAG, "Inserted static data into table " + TABLE_AIUEO_GROUPS);

        // insert into table aiueo_types
        database.execSQL("INSERT INTO " + TABLE_AIUEO_TYPES + " VALUES (0, \"hiragana\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_TYPES + " VALUES (1, \"katakana\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_TYPES + " VALUES (2, \"romaji\")");
        Log.v(TAG, "Inserted static data into table " + TABLE_AIUEO_TYPES);

        // insert into table aiueo_rows
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (0, 0, 0, \"あ\", \"い\", \"う\", \"え\", \"お\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (1, 0, 2, \"a\", \"i\", \"u\", \"e\", \"o\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (2, 1, 0, \"か\", \"き\", \"く\", \"け\", \"こ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (3, 1, 2, \"ka\", \"ki\", \"ku\", \"ke\", \"ko\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (4, 2, 0, \"が\", \"ぎ\", \"ぐ\", \"げ\", \"ご\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (5, 2, 2, \"ga\", \"gi\", \"gu\", \"ge\", \"go\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (6, 3, 0, \"さ\", \"し\", \"す\", \"せ\", \"そ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (7, 3, 2, \"sa\", \"shi\", \"su\", \"se\", \"so\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (8, 4, 0, \"ざ\", \"じ\", \"ず\", \"ぜ\", \"ぞ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (9, 4, 2, \"za\", \"ji\", \"zu\", \"ze\", \"zo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (10, 5, 0, \"た\", \"ち\", \"つ\", \"て\", \"と\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (11, 5, 2, \"ta\", \"chi\", \"tsu\", \"te\", \"to\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (12, 6, 0, \"だ\", \"ぢ\", \"づ\", \"で\", \"ど\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (13, 6, 2, \"da\", \"ji\", \"zu\", \"de\", \"do\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (14, 7, 0, \"な\", \"に\", \"ぬ\", \"ね\", \"の\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (15, 7, 2, \"na\", \"ni\", \"nu\", \"ne\", \"no\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (16, 8, 0, \"は\", \"ひ\", \"ふ\", \"へ\", \"ほ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (17, 8, 2, \"ha\", \"hi\", \"fu\", \"he\", \"ho\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (18, 9, 0, \"ば\", \"び\", \"ぶ\", \"べ\", \"ぼ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (19, 9, 2, \"ba\", \"bi\", \"bu\", \"be\", \"bo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (20, 10, 0, \"ぱ\", \"ぴ\", \"ぷ\", \"ぺ\", \"ぽ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (21, 10, 2, \"pa\", \"pi\", \"pu\", \"pe\", \"po\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (22, 11, 0, \"ま\", \"み\", \"む\", \"め\", \"も\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (23, 11, 2, \"ma\", \"mi\", \"mu\", \"me\", \"mo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (24, 12, 0, \"や\", \"\", \"ゆ\", \"\", \"よ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (25, 12, 2, \"ya\", \"\", \"yu\", \"\", \"yo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (26, 13, 0, \"ら\", \"り\", \"る\", \"れ\", \"ろ\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (27, 13, 2, \"ra\", \"ri\", \"ru\", \"re\", \"ro\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (28, 14, 0, \"わ\", \"\", \"\", \"\", \"を\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (29, 14, 2, \"wa\", \"\", \"\", \"\", \"wo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (30, 15, 0, \"ん\", \"\", \"\", \"\", \"\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEO_ROWS + " VALUES (31, 15, 2, \"n\", \"\", \"\", \"\", \"\")");
        Log.v(TAG, "Inserted static data into table " + TABLE_AIUEO_ROWS);
    }

    public static String[] allAIUEOGroupColumns(){
        return new String[] {COLUMN_AIUEO_GROUP_ID, COLUMN_AIUEO_GROUP};
    }

    public static String[] allAIUEOTypeColumns(){
        return new String[] {COLUMN_AIUEO_TYPE_ID, COLUMN_AIUEO_TYPE};
    }

    public static String[] allAIUEORowColumns(){
        return new String[] {COLUMN_AIUEO_ROW_ID, COLUMN_AIUEO_ROW_GROUP_ID, COLUMN_AIUEO_ROW_TYPE_ID,
                                COLUMN_VOWEL_A, COLUMN_VOWEL_I, COLUMN_VOWEL_U, COLUMN_VOWEL_E, COLUMN_VOWEL_O };
    }
}