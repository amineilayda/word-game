package com.example.wordgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordgame.database.DatabaseHelper;
import com.example.wordgame.engine.UserManager;


public class MainActivity extends AppCompatActivity {

    Button btnNewGame, btnScore;
    TextView txtUser;
    Button btnChangeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Log.d("TEST", "onCreate çalıştı");
        DatabaseHelper db = new DatabaseHelper(this);
        Log.d("DB_TEST", "Kayıt sayısı: " + db.getAllScores().size());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUser);
        btnNewGame = findViewById(R.id.btnNewGame);
        btnScore = findViewById(R.id.btnScore);

        btnChangeUser = findViewById(R.id.btnChangeUser);

        String name = UserManager.getUsername(this);

        if (name == null || name.trim().isEmpty()) {
            startActivity(new Intent(this, UsernameActivity.class));
            finish();
            return;
        }

        updateUsername();

        txtUser.setOnClickListener(v ->
                startActivity(new Intent(this, UsernameActivity.class)));

        btnNewGame.setOnClickListener(v ->
                startActivity(new Intent(this, LevelSelectActivity.class)));

        btnScore.setOnClickListener(v ->
                startActivity(new Intent(this, ScoreBoardActivity.class)));



        btnChangeUser.setOnClickListener(v -> {
            Intent intent = new Intent(this, UsernameActivity.class);
            intent.putExtra("editMode", true);
            startActivity(intent);
        });
    }


    @Override
    protected void onResume() {  //başka ekrandan geri gelince çalışır
        super.onResume();
        updateUsername();
    }

    private void updateUsername() {
        String name = UserManager.getUsername(this);

        if (name == null || name.trim().isEmpty()) {
            name = "Player";
        }

        txtUser.setText(name);
    }
}