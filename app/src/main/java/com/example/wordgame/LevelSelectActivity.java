package com.example.wordgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wordgame.GameActivity;
import com.example.wordgame.R;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
    }


    public void easy(View v) {
        startGame(10, 10, 25);
    }


    public void medium(View v) {
        startGame(8, 8, 20);
    }

    public void hard(View v) {
        startGame(6, 6, 15);
    }

    private void startGame(int rows, int cols, int moves) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("rows", rows);
        intent.putExtra("cols", cols);
        intent.putExtra("moves", moves);
        startActivity(intent);
    }
}