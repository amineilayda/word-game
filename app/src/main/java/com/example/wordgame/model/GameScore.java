package com.example.wordgame.model;

public class GameScore {

    private String username;
    private int score;
    private String date;
    private int gridSize;
    private int wordCount;
    private String longestWord;
    private long duration;

    public GameScore(String username,
                     int score,
                     String date,
                     int gridSize,
                     int wordCount,
                     String longestWord,
                     long duration) {

        this.username = username;
        this.score = score;
        this.date = date;
        this.gridSize = gridSize;
        this.wordCount = wordCount;
        this.longestWord = longestWord;
        this.duration = duration;
    }

    public String getUsername() { return username; }
    public int getScore() { return score; }
    public String getDate() { return date; }
    public int getGridSize() { return gridSize; }
    public int getWordCount() { return wordCount; }
    public String getLongestWord() { return longestWord; }
    public long getDuration() { return duration; }
}