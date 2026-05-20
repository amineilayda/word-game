package com.example.wordgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordgame.database.DatabaseHelper;
import com.example.wordgame.engine.GameEngine;
import com.example.wordgame.engine.GridManager;
import com.example.wordgame.engine.ScoreManager;
import com.example.wordgame.engine.UserManager;
import com.example.wordgame.engine.WordListManager;
import com.example.wordgame.model.GameScore;
import com.example.wordgame.model.LetterTile;
import com.example.wordgame.ui.GameGridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameActivity extends AppCompatActivity {

    private GameEngine engine;
    private GridManager gridManager;
    private WordListManager wordManager;
    private GameGridView gameGridView;

    private TextView tvScore, tvMoves, tvCurrentWord, tvWordCount;
    private boolean gameEnded = false;


    private int rows;
    private int cols;


    private String longestWord = "";
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        startTime = System.currentTimeMillis();


        rows = getIntent().getIntExtra("rows", 10);
        cols = getIntent().getIntExtra("cols", 10);
        int initialMoves = getIntent().getIntExtra("moves", 25);

        engine = new GameEngine(initialMoves);
        gridManager = new GridManager(rows, cols);


        tvScore = findViewById(R.id.tvScore);
        tvMoves = findViewById(R.id.tvMoves);
        tvCurrentWord = findViewById(R.id.tvCurrentWord);
        tvWordCount = findViewById(R.id.tvWordCount);
        gameGridView = findViewById(R.id.gameGridView);


        new Thread(() -> {

            wordManager = new WordListManager(this);
            gridManager.initializeGrid();

            LetterTile[][] tiles = gridManager.getLetterTiles();

            runOnUiThread(() -> {
                gameGridView.setGridData(rows, cols, tiles);
                updateUI();
            });

        }).start();
    }


    private void updateUI() {
        tvScore.setText("Puan: " + engine.getCurrentScore());
        tvMoves.setText("Hamle: " + engine.getRemainingMoves());
        tvWordCount.setText("Kelime Sayısı: " + engine.getWordCount());
    }


    public void onWordSelected(String word, ArrayList<LetterTile> path) {

        engine.useMove();

        tvCurrentWord.setText(word);

        if (wordManager.isValidWord(word)) {

            engine.increaseWordCount();

            if (word.length() > longestWord.length()) {
                longestWord = word;
            }

            int points = engine.calculateWordScore(word);
            engine.addScore(points);

            new Thread(() -> {

                gridManager.clearCells(path);
                gridManager.applyGravityAndFill();

                runOnUiThread(() -> {

                    updateGridView();
                    updateUI();

                    Toast.makeText(GameActivity.this,
                            word + " +" + points,
                            Toast.LENGTH_SHORT).show();

                    checkGameStatus();
                });

            }).start();

        } else {

            updateUI();

            Toast.makeText(this,
                    "Geçersiz kelime",
                    Toast.LENGTH_SHORT).show();

            checkGameStatus();
        }
    }


    private void updateGridView() {
        LetterTile[][] currentTiles = gridManager.getLetterTiles();
        gameGridView.setGridData(rows, cols, currentTiles);
        gameGridView.invalidate();
    }


    private void checkGameStatus() {

        if (gameEnded) return;

        if (engine.getRemainingMoves() <= 0) {

            gameEnded = true;

            long duration = System.currentTimeMillis() - startTime;

            String date = new SimpleDateFormat("dd.MM.yyyy")
                    .format(new Date());

            GameScore score = new GameScore(
                    UserManager.getUsername(this),
                    engine.getCurrentScore(),
                    date,
                    rows,
                    engine.getWordCount(),
                    longestWord,
                    duration
            );

            try {
                DatabaseHelper db = new DatabaseHelper(this);
                db.addScore(score);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Toast.makeText(this,
                    "Oyun bitti skor " + engine.getCurrentScore(),
                    Toast.LENGTH_LONG).show();

            startActivity(new Intent(this, ScoreBoardActivity.class));
            finish();
        }
    }


}