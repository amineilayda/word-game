package com.example.wordgame.engine;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//puanlama kalan hamle hesaplama
public class GameEngine {

    private int currentScore = 0;
    private int remainingMoves;
    private int wordCount = 0;

    private final Map<Character, Integer> letterPoints = new HashMap<>();

    public GameEngine(int initialMoves) {
        this.remainingMoves = initialMoves;
        setupLetterPoints();
    }

    //harf puanlama map içine tutuyor puan değerlerini
    private void setupLetterPoints() {

        String p1 = "AEİLNRS T";
        for (char c : p1.replace(" ", "").toCharArray()) {
            letterPoints.put(c, 1);
        }

        String p2 = "ÇŞİMOÜU";
        for (char c : p2.toCharArray()) {
            letterPoints.put(c, 2);
        }

        String p3 = "BCDFG";
        for (char c : p3.toCharArray()) {
            letterPoints.put(c, 3);
        }

        String p4 = "JHK";
        for (char c : p4.toCharArray()) {
            letterPoints.put(c, 4);
        }

        String p5 = "ĞPVZ";
        for (char c : p5.toCharArray()) {
            letterPoints.put(c, 5);
        }
    }

    // puanı hesaplama
    public int calculateWordScore(String word) {

        if (word == null) return 0;

        int score = 0;

        String cleanWord = word
                .toUpperCase(Locale.forLanguageTag("tr"))
                .replaceAll("\\s+", "");

        for (char c : cleanWord.toCharArray()) {
            score += letterPoints.getOrDefault(c, 1); //listede değilse 1 ver
        }

        return score;
    }

    // hamle
    public void useMove() {
        if (remainingMoves > 0) {
            remainingMoves--;
        }
    }


    public void increaseWordCount() {
        wordCount++;
    }

    public int getWordCount() {
        return wordCount;
    }


    public void addScore(int points) {
        currentScore += points;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getRemainingMoves() {
        return remainingMoves;
    }
}