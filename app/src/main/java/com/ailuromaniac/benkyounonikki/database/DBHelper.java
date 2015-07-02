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

    // table aiueos
    public static final String TABLE_AIUEOS = "aiueos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GROUP = "aiueo_group";
    public static final String COLUMN_HIRAGANA_A = "aiueo_hiragana_a";
    public static final String COLUMN_HIRAGANA_I = "aiueo_hiragana_i";
    public static final String COLUMN_HIRAGANA_U = "aiueo_hiragana_u";
    public static final String COLUMN_HIRAGANA_E = "aiueo_hiragana_e";
    public static final String COLUMN_HIRAGANA_O = "aiueo_hiragana_o";
    public static final String COLUMN_KATAKANA_A = "aiueo_katakana_a";
    public static final String COLUMN_KATAKANA_I = "aiueo_katakana_i";
    public static final String COLUMN_KATAKANA_U = "aiueo_katakana_u";
    public static final String COLUMN_KATAKANA_E = "aiueo_katakana_e";
    public static final String COLUMN_KATAKANA_O = "aiueo_katakana_o";
    public static final String COLUMN_ROMAJI_A = "aiueo_romaji_a";
    public static final String COLUMN_ROMAJI_I = "aiueo_romaji_i";
    public static final String COLUMN_ROMAJI_U = "aiueo_romaji_u";
    public static final String COLUMN_ROMAJI_E = "aiueo_romaji_e";
    public static final String COLUMN_ROMAJI_O = "aiueo_romaji_o";

    // SQL statement of the employees table creation
    private static final String SQL_CREATE_TABLE_AIUEOS = "CREATE TABLE " + TABLE_AIUEOS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_GROUP + " TEXT NOT NULL, "
            + COLUMN_HIRAGANA_A + " TEXT, "
            + COLUMN_HIRAGANA_I + " TEXT, "
            + COLUMN_HIRAGANA_U + " TEXT, "
            + COLUMN_HIRAGANA_E + " TEXT, "
            + COLUMN_HIRAGANA_O + " TEXT, "
            + COLUMN_KATAKANA_A + " TEXT, "
            + COLUMN_KATAKANA_I + " TEXT, "
            + COLUMN_KATAKANA_U + " TEXT, "
            + COLUMN_KATAKANA_E + " TEXT, "
            + COLUMN_KATAKANA_O + " TEXT, "
            + COLUMN_ROMAJI_A + " TEXT, "
            + COLUMN_ROMAJI_I + " TEXT, "
            + COLUMN_ROMAJI_U + " TEXT, "
            + COLUMN_ROMAJI_E + " TEXT, "
            + COLUMN_ROMAJI_O + " TEXT "
            + ");";

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

    private void populateStaticData(SQLiteDatabase database) {
        // insert into table aiueo_rows
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (0, \"a\",\"あ\", \"い\", \"う\", \"え\", \"お\", " +
                                                                            "\"ア\", \"イ\", \"ウ\", \"エ\", \"オ\", " +
                                                                            "\"a\", \"i\", \"u\", \"e\", \"o\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (1, \"ka\", \"か\", \"き\", \"く\", \"け\", \"こ\", " +
                                                                            "\"カ\", \"キ\", \"ク\", \"ケ\", \"コ\", " +
                                                                            "\"ka\", \"ki\", \"ku\", \"ke\", \"ko\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (2, \"ga\", \"が\", \"ぎ\", \"ぐ\", \"げ\", \"ご\", " +
                                                                            "\"ガ\", \"ギ\", \"グ\", \"ゲ\", \"ゴ\", " +
                                                                            "\"ga\", \"gi\", \"gu\", \"ge\", \"go\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (3, \"sa\", \"さ\", \"し\", \"す\", \"せ\", \"そ\", " +
                                                                            "\"サ\", \"シ\", \"ス\", \"セ\", \"ソ\", " +
                                                                            "\"sa\", \"shi\", \"su\", \"se\", \"so\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (4, \"za\", \"ざ\", \"じ\", \"ず\", \"ぜ\", \"ぞ\", " +
                                                                            "\"ザ\", \"ジ\", \"ズ\", \"ゼ\", \"ゾ\", " +
                                                                            "\"za\", \"ji\", \"zu\", \"ze\", \"zo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (5, \"ta\", \"た\", \"ち\", \"つ\", \"て\", \"と\", " +
                                                                            "\"タ\", \"チ\", \"ツ\", \"テ\", \"ト\", " +
                                                                            "\"ta\", \"chi\", \"tsu\", \"te\", \"to\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (6, \"da\", \"だ\", \"ぢ\", \"づ\", \"で\", \"ど\", " +
                                                                            "\"ダ\", \"ヂ\", \"ヅ\", \"デ\", \"ド\", " +
                                                                            "\"da\", \"ji\", \"zu\", \"de\", \"do\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (7, \"na\", \"な\", \"に\", \"ぬ\", \"ね\", \"の\", " +
                                                                            "\"ナ\", \"ニ\", \"ヌ\", \"ネ\", \"ノ\", " +
                                                                            "\"na\", \"ni\", \"nu\", \"ne\", \"no\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (8, \"ha\", \"は\", \"ひ\", \"ふ\", \"へ\", \"ほ\", " +
                                                                            "\"ハ\", \"ヒ\", \"フ\", \"ヘ\", \"ホ\", " +
                                                                            "\"ha\", \"hi\", \"fu\", \"he\", \"ho\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (9, \"ba\", \"ば\", \"び\", \"ぶ\", \"べ\", \"ぼ\", " +
                                                                            "\"バ\", \"ビ\", \"ブ\", \"ベ\", \"ボ\", " +
                                                                            "\"ba\", \"bi\", \"bu\", \"be\", \"bo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (10, \"pa\", \"ぱ\", \"ぴ\", \"ぷ\", \"ぺ\", \"ぽ\", " +
                                                                            "\"パ\", \"ピ\", \"プ\", \"ペ\", \"ポ\", " +
                                                                            "\"pa\", \"pi\", \"pu\", \"pe\", \"po\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (11, \"ma\", \"ま\", \"み\", \"む\", \"め\", \"も\", " +
                                                                            "\"マ\", \"ミ\", \"ム\", \"メ\", \"モ\", " +
                                                                            "\"ma\", \"mi\", \"mu\", \"me\", \"mo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (12, \"ya\", \"や\", \"\", \"ゆ\", \"\", \"よ\", " +
                                                                            "\"ヤ\", \"\", \"ユ\", \"\", \"ヨ\", " +
                                                                            "\"ya\", \"\", \"yu\", \"\", \"yo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (13, \"ra\", \"ら\", \"り\", \"る\", \"れ\", \"ろ\", " +
                                                                            "\"ラ\", \"リ\", \"ル\", \"レ\", \"ロ\", " +
                                                                            "\"ra\", \"ri\", \"ru\", \"re\", \"ro\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (14, \"wa\", \"わ\", \"\", \"\", \"\", \"を\", " +
                                                                            "\"ワ\", \"\", \"\", \"\", \"ヲ\", " +
                                                                            "\"wa\", \"\", \"\", \"\", \"wo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (15, \"n\", \"ん\", \"\", \"\", \"\", \"\", " +
                                                                            "\"ン\", \"\", \"\", \"\", \"\", " +
                                                                            "\"n\", \"\", \"\", \"\", \"\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (16, \"kya\", \"きゃ\", \"\", \"きゅ\", \"\", \"きょ\", " +
                                                                            "\"キャ\", \"\", \"キュ\", \"\", \"キョ\", " +
                                                                            "\"kya\", \"\", \"kyu\", \"\", \"kyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (17, \"gya\", \"ぎゃ\", \"\", \"ぎゅ\", \"\", \"ぎょ\", " +
                                                                            "\"ギャ\", \"\", \"ギュ\", \"\", \"ギョ\", " +
                                                                            "\"gya\", \"\", \"gyu\", \"\", \"gyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (18, \"sha\", \"しゃ\", \"\", \"しゅ\", \"\", \"しょ\", " +
                                                                            "\"シャ\", \"\", \"シ\", \"\", \"ショ\", " +
                                                                            "\"sha\", \"\", \"shu\", \"\", \"sho\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (19, \"ja\", \"じゃ\", \"\", \"じゅ\", \"\", \"じょ\", " +
                                                                            "\"ジャ\", \"\", \"ジュ\", \"\", \"ジョ\", " +
                                                                            "\"ja\", \"\", \"ju\", \"\", \"jo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (20, \"cha\", \"ちゃ\", \"\", \"ちゅ\", \"\", \"ちょ\", " +
                                                                            "\"チャ\", \"\", \"チュ\", \"\", \"チョ\", " +
                                                                            "\"cha\", \"\", \"chu\", \"\", \"cho\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (21, \"nya\", \"にゃ\", \"\", \"にゅ\", \"\", \"にょ\", " +
                                                                            "\"ニャ\", \"\", \"ニュ\", \"\", \"ニョ\", " +
                                                                            "\"nya\", \"\", \"nyu\", \"\", \"nyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (22, \"hya\", \"ひゃ\", \"\", \"ひゅ\", \"\", \"ひょ\", " +
                                                                            "\"ヒャ\", \"\", \"ヒュ\", \"\", \"ヒョ\", " +
                                                                            "\"hya\", \"\", \"hyu\", \"\", \"hyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (23, \"bya\", \"びゃ\", \"\", \"びゅ\", \"\", \"びょ\", " +
                                                                            "\"ビャ\", \"\", \"ビュ\", \"\", \"ビョ\", " +
                                                                            "\"bya\", \"\", \"byu\", \"\", \"byo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (24, \"pya\", \"ぴゃ\", \"\", \"ぴゅ\", \"\", \"ぴょ\", " +
                                                                            "\"ピャ\", \"\", \"ピュ\", \"\", \"ピョ\", " +
                                                                            "\"pya\", \"\", \"pyu\", \"\", \"pyo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (25, \"mya\", \"みゃ\", \"\", \"みゅ\", \"\", \"みょ\", " +
                                                                            "\"ミャ\", \"\", \"ミュ\", \"\", \"ミョ\", " +
                                                                            "\"mya\", \"\", \"myu\", \"\", \"myo\")");
        database.execSQL("INSERT INTO " + TABLE_AIUEOS + " VALUES (26, \"rya\", \"りゃ\", \"\", \"りゅ\", \"\", \"りょ\", " +
                                                                            "\"リャ\", \"\", \"リュ\", \"\", \"リョ\", " +
                                                                            "\"rya\", \"\", \"ryu\", \"\", \"ryo\")");
        Log.v(TAG, "Inserted static data into table " + TABLE_AIUEOS);
    }

    public static String[] allAIUEOColumns(){
        return new String[] {COLUMN_ID, COLUMN_GROUP, COLUMN_HIRAGANA_A, COLUMN_HIRAGANA_I, COLUMN_HIRAGANA_U, COLUMN_HIRAGANA_E,
                                COLUMN_HIRAGANA_O, COLUMN_KATAKANA_A, COLUMN_KATAKANA_I, COLUMN_KATAKANA_U, COLUMN_KATAKANA_E,
                                COLUMN_KATAKANA_O, COLUMN_ROMAJI_A, COLUMN_ROMAJI_I, COLUMN_ROMAJI_U, COLUMN_ROMAJI_E, COLUMN_ROMAJI_O};
    }
}