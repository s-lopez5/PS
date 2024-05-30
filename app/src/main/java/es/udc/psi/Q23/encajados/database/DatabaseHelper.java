package es.udc.psi.Q23.encajados.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Tetris.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_SCORES = "scores";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "username";
    private static final String COLUMN_SCORE = "score";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SCORES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_SCORE + " INTEGER" +
                    ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    public void addScore(String name, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SCORE, score);
        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    public Cursor getAllScores() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_SCORES, null, null, null, null, null, COLUMN_SCORE + " DESC");
    }

    public String getHighestScore(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(" + COLUMN_SCORE + ") FROM " + TABLE_SCORES + " WHERE " + COLUMN_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        int highestScore = 0;
        if (cursor.moveToFirst()) {
            highestScore = cursor.getInt(0);
        }
        cursor.close();
        db.close();

        return String.valueOf(highestScore);

    }
}
