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

    // table aiueos
    public static final String TABLE_AIUEOS = "aiueos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GROUP = "aiueo_group";
    public static final String COLUMN_HIRAGANA_A = "aiueo_hiragana_a";
    public static final String COLUMN_HIRAGANA_I = "aiueo_hiragana_i";
    public static final String COLUMN_HIRAGANA_U = "aiueo_hiragana_u";
    public static final String COLUMN_HIRAGANA_E = "aiueo_hiragana_e";
    public static final String COLUMN_HIRAGANA_O = "aiueo_hiragana_o";
    public static final String COLUMN_ROMAJI_A = "aiueo_romaji_a";
    public static final String COLUMN_ROMAJI_I = "aiueo_romaji_i";
    public static final String COLUMN_ROMAJI_U = "aiueo_romaji_u";
    public static final String COLUMN_ROMAJI_E = "aiueo_romaji_e";
    public static final String COLUMN_ROMAJI_O = "aiueo_romaji_o";


    private static final String DATABASE_NAME = "benkyounonikki.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the employees table creation
    private static final String SQL_CREATE_TABLE_AIUEOS = "CREATE TABLE " + TABLE_AIUEOS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_GROUP + " TEXT NOT NULL, "
            + COLUMN_HIRAGANA_A + " TEXT, "
            + COLUMN_HIRAGANA_I + " TEXT, "
            + COLUMN_HIRAGANA_U + " TEXT, "
            + COLUMN_HIRAGANA_E + " TEXT, "
            + COLUMN_HIRAGANA_O + " TEXT, "
            + COLUMN_ROMAJI_A + " TEXT, "
            + COLUMN_ROMAJI_I + " TEXT, "
            + COLUMN_ROMAJI_U + " TEXT, "
            + COLUMN_ROMAJI_E + " TEXT, "
            + COLUMN_ROMAJI_O + " TEXT "
            +");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_AIUEOS);
        Log.v(TAG, "Created table: " + SQL_CREATE_TABLE_AIUEOS);

        populateStaticData(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to " + newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIUEOS);

        // recreate the tables
        onCreate(db);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    private void populateStaticData(SQLiteDatabase database) {
        // insert into table aiueo_rows
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (0, \"a\",\"あ\", \"い\", \"う\", \"え\", \"お\", " +
                                                                            "\"a\", \"i\", \"u\", \"e\", \"o\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (1, \"ka\", \"か\", \"き\", \"く\", \"け\", \"こ\", " +
                                                                            "\"ka\", \"ki\", \"ku\", \"ke\", \"ko\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (2, \"ga\", \"が\", \"ぎ\", \"ぐ\", \"げ\", \"ご\", " +
                                                                            "\"ga\", \"gi\", \"gu\", \"ge\", \"go\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (3, \"sa\", \"さ\", \"し\", \"す\", \"せ\", \"そ\", " +
                                                                            "\"sa\", \"shi\", \"su\", \"se\", \"so\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (4, \"za\", \"ざ\", \"じ\", \"ず\", \"ぜ\", \"ぞ\", " +
                                                                            "\"za\", \"ji\", \"zu\", \"ze\", \"zo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (5, \"ta\", \"た\", \"ち\", \"つ\", \"て\", \"と\", " +
                                                                            "\"ta\", \"chi\", \"tsu\", \"te\", \"to\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (6, \"da\", \"だ\", \"ぢ\", \"づ\", \"で\", \"ど\", " +
                                                                            "\"da\", \"ji\", \"zu\", \"de\", \"do\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (7, \"na\", \"な\", \"に\", \"ぬ\", \"ね\", \"の\", " +
                                                                            "\"na\", \"ni\", \"nu\", \"ne\", \"no\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (8, \"ha\", \"は\", \"ひ\", \"ふ\", \"へ\", \"ほ\", " +
                                                                            "\"ha\", \"hi\", \"fu\", \"he\", \"ho\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (9, \"ba\", \"ば\", \"び\", \"ぶ\", \"べ\", \"ぼ\", " +
                                                                            "\"ba\", \"bi\", \"bu\", \"be\", \"bo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (10, \"pa\", \"ぱ\", \"ぴ\", \"ぷ\", \"ぺ\", \"ぽ\", " +
                                                                            "\"pa\", \"pi\", \"pu\", \"pe\", \"po\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (11, \"ma\", \"ま\", \"み\", \"む\", \"め\", \"も\", " +
                                                                            "\"ma\", \"mi\", \"mu\", \"me\", \"mo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (12, \"ya\", \"や\", \"\", \"ゆ\", \"\", \"よ\", " +
                                                                            "\"ya\", \"\", \"yu\", \"\", \"yo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (13, \"ra\", \"ら\", \"り\", \"る\", \"れ\", \"ろ\", " +
                                                                            "\"ra\", \"ri\", \"ru\", \"re\", \"ro\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (14, \"wa\", \"わ\", \"\", \"\", \"\", \"を\", " +
                                                                            "\"wa\", \"\", \"\", \"\", \"wo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (15, \"n\", \"ん\", \"\", \"\", \"\", \"\", " +
                                                                            "\"n\", \"\", \"\", \"\", \"\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (16, \"kya\", \"きゃ\", \"\", \"きゅ\", \"\", \"きょ\", " +
                                                                            "\"kya\", \"\", \"kyu\", \"\", \"kyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (17, \"gya\", \"ぎゃ\", \"\", \"ぎゅ\", \"\", \"ぎょ\", " +
                                                                            "\"gya\", \"\", \"gyu\", \"\", \"gyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (18, \"sha\", \"しゃ\", \"\", \"しゅ\", \"\", \"しょ\", " +
                                                                            "\"sha\", \"\", \"shu\", \"\", \"sho\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (19, \"ja\", \"じゃ\", \"\", \"じゅ\", \"\", \"じょ\", " +
                                                                            "\"ja\", \"\", \"ju\", \"\", \"jo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (20, \"cha\", \"ちゃ\", \"\", \"ちゅ\", \"\", \"ちょ\", " +
                                                                            "\"cha\", \"\", \"chu\", \"\", \"cho\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (21, \"nya\", \"にゃ\", \"\", \"にゅ\", \"\", \"にょ\", " +
                                                                            "\"nya\", \"\", \"nyu\", \"\", \"nyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (22, \"hya\", \"ひゃ\", \"\", \"ひゅ\", \"\", \"ひょ\", " +
                                                                            "\"hya\", \"\", \"hyu\", \"\", \"hyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (23, \"bya\", \"びゃ\", \"\", \"びゅ\", \"\", \"びょ\", " +
                                                                            "\"bya\", \"\", \"byu\", \"\", \"byo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (24, \"pya\", \"ぴゃ\", \"\", \"ぴゅ\", \"\", \"ぴょ\", " +
                                                                            "\"pya\", \"\", \"pyu\", \"\", \"pyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (25, \"mya\", \"みゃ\", \"\", \"みゅ\", \"\", \"みょ\", " +
                                                                            "\"mya\", \"\", \"myu\", \"\", \"myo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (26, \"rya\", \"りゃ\", \"\", \"りゅ\", \"\", \"りょ\", " +
                                                                            "\"rya\", \"\", \"ryu\", \"\", \"ryo\")");
        Log.v(TAG, "Inserted static data into table " + TABLE_AIUEOS);
    }

    public static String[] allAIUEOColumns(){
        return new String[] {COLUMN_ID, COLUMN_GROUP, COLUMN_HIRAGANA_A, COLUMN_HIRAGANA_I, COLUMN_HIRAGANA_U, COLUMN_HIRAGANA_E,
                                COLUMN_HIRAGANA_O, COLUMN_ROMAJI_A, COLUMN_ROMAJI_I, COLUMN_ROMAJI_U, COLUMN_ROMAJI_E, COLUMN_ROMAJI_O};
    }
}