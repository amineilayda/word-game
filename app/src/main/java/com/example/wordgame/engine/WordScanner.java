package com.example.wordgame.engine;

import android.content.Context;

import com.example.wordgame.model.LetterTile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordScanner {

    private WordListManager wordList;

    public WordScanner(Context context) {
        wordList = new WordListManager(context);
    }

    public List<String> findAllWords(LetterTile[][] grid) {

        List<String> foundWords = new ArrayList<>();

        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(grid, r, c, "", visited, foundWords);
            }
        }

        return foundWords;
    }

    private void dfs(LetterTile[][] grid, int r, int c,
                     String current,
                     boolean[][] visited,
                     List<String> foundWords) {

        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length)
            return;

        if (visited[r][c]) return;

        current += grid[r][c].getLetter();

        // sözlük kont
        if (current.length() >= 2 && wordList.isValidWord(current)) {
            if (!foundWords.contains(current)) {
                foundWords.add(current);
            }
        }

        visited[r][c] = true;

        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            dfs(grid, r + dr[i], c + dc[i], current, visited, foundWords);
        }

        visited[r][c] = false;
    }
}