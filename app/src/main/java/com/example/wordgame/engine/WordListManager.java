package com.example.wordgame.engine;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class WordListManager {

    private HashSet<String> dictionary = new HashSet<>();
    private Set<String> prefixSet = new HashSet<>();

    public WordListManager(Context context) {
        loadDictionary(context);
    }

    private void loadDictionary(Context context) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("tr.txt"), "UTF-8")
            );

            String line;

            while ((line = reader.readLine()) != null) {

                String word = normalize(line);

                if (!word.isEmpty()) {

                    dictionary.add(word);

                    for (int i = 1; i <= word.length(); i++) {
                        prefixSet.add(word.substring(0, i));
                    }
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidWord(String word) {
        if (word == null) return false;

        word = normalize(word);

        return word.length() >= 3 && dictionary.contains(word);
    }

    public boolean isValidPrefix(String prefix) {
        if (prefix == null) return false;

        prefix = normalize(prefix);

        return prefixSet.contains(prefix);
    }

    private String normalize(String text) {
        return text.trim().toUpperCase(new Locale("tr", "TR"));
    }
}
