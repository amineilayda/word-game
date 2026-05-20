package com.example.wordgame.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.example.wordgame.model.GameScore;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "scores.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE scores (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username TEXT," +
                        "score INTEGER," +
                        "date TEXT," +
                        "grid INTEGER," +
                        "wordCount INTEGER," +
                        "longestWord TEXT," +
                        "duration INTEGER" +
                        ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS scores");
        onCreate(db);
    }


    public void addScore(GameScore s) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", s.getUsername());
        values.put("score", s.getScore());
        values.put("date", s.getDate());
        values.put("grid", s.getGridSize());
        values.put("wordCount", s.getWordCount());
        values.put("longestWord", s.getLongestWord());
        values.put("duration", s.getDuration());

        db.insert("scores", null, values);
        db.close();

        long result = db.insert("scores", null, values);

        Log.d("DB_TEST", "Insert result: " + result);

    }


    public List<GameScore> getAllScores() {
        List<GameScore> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM scores ORDER BY id DESC", null);

        if (cursor.moveToFirst()) {
            do {
                GameScore s = new GameScore(
                        cursor.getString(cursor.getColumnIndexOrThrow("username")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("score")),
                        cursor.getString(cursor.getColumnIndexOrThrow("date")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("grid")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("wordCount")),
                        cursor.getString(cursor.getColumnIndexOrThrow("longestWord")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("duration"))
                );

                list.add(s);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }

}