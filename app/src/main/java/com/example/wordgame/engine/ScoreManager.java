package com.example.wordgame.engine;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wordgame.model.GameScore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager {

    private static final String PREF = "scores";
    private static final String KEY = "score_list";

    public static void addScore(Context context, GameScore s) {
        try {
            List<GameScore> list = getScores(context);
            list.add(s);

            JSONArray arr = new JSONArray();

            for (GameScore sc : list) {
                JSONObject obj = new JSONObject();
                obj.put("username", sc.getUsername());
                obj.put("score", sc.getScore());
                obj.put("date", sc.getDate());
                obj.put("gridSize", sc.getGridSize());
                obj.put("wordCount", sc.getWordCount());
                obj.put("longestWord", sc.getLongestWord());
                obj.put("duration", sc.getDuration());
                arr.put(obj);
            }

            SharedPreferences prefs =
                    context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

            prefs.edit().putString(KEY, arr.toString()).apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<GameScore> getScores(Context context) {
        List<GameScore> list = new ArrayList<>();

        try {
            SharedPreferences prefs =
                    context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

            String json = prefs.getString(KEY, "[]");
            JSONArray arr = new JSONArray(json);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);

                GameScore s = new GameScore(
                        o.getString("username"),
                        o.getInt("score"),
                        o.getString("date"),
                        o.getInt("gridSize"),
                        o.getInt("wordCount"),
                        o.getString("longestWord"),
                        o.getLong("duration")
                );

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void clearScores(Context context) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}