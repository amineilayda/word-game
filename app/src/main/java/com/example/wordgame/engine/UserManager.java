package com.example.wordgame.engine;

import android.content.Context;
import android.content.SharedPreferences;

public class UserManager {

    private static final String PREF = "wordgame_prefs";
    private static final String KEY = "username";

    public static void saveUsername(Context context, String name) {
        SharedPreferences prefs =
                context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        prefs.edit().putString(KEY, name).apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        return prefs.getString(KEY, null);
    }

    public static boolean hasUsername(Context context) {
        String name = getUsername(context);
        return name != null && !name.trim().isEmpty();
    }


}