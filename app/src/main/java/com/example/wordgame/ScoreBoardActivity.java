package com.example.wordgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.adapter.ScoreAdapter;
import com.example.wordgame.database.DatabaseHelper;
import com.example.wordgame.model.GameScore;

import java.util.Collections;
import java.util.List;

public class ScoreBoardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ScoreAdapter adapter;
    TextView txtSummary;
    Button btnBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        recyclerView = findViewById(R.id.recyclerView);
        txtSummary = findViewById(R.id.txtSummary);
        btnBackToMenu = findViewById(R.id.btnBack);


        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        DatabaseHelper db = new DatabaseHelper(this);
        List<GameScore> list = db.getAllScores();


        Collections.reverse(list);


        loadStats(list);


        adapter = new ScoreAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void loadStats(List<GameScore> list) {
        int totalGames = list.size();
        int maxScore = 0;
        int totalScore = 0;
        int totalWords = 0;
        long totalDuration = 0;
        String longestWord = "";

        for (GameScore s : list) {
            totalScore += s.getScore();
            totalWords += s.getWordCount();
            totalDuration += s.getDuration();

            if (s.getScore() > maxScore)
                maxScore = s.getScore();

            if (s.getLongestWord() != null &&
                    s.getLongestWord().length() > longestWord.length()) {
                longestWord = s.getLongestWord();
            }
        }

        int avg = totalGames == 0 ? 0 : totalScore / totalGames;

        txtSummary.setText(
                "GENEL İSTATİSTİKLER\n" +
                        "━━━━━━━━━━━━━━━━━━━━\n" +
                        "• Toplam Oyun: " + totalGames +
                        "\n• En Yüksek Puan: " + maxScore +
                        "\n• Ortalama Puan: " + avg +
                        "\n• Toplam Kelime: " + totalWords +
                        "\n• En Uzun Kelime: " + longestWord +
                        "\n• Toplam Süre: " + (totalDuration / 60000) + " dk"
        );
    }
}