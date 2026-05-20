package com.example.wordgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordgame.engine.UserManager;

public class UsernameActivity extends AppCompatActivity {

    EditText editName;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        editName = findViewById(R.id.editName);
        btnStart = findViewById(R.id.btnStart);

        boolean editMode = getIntent().getBooleanExtra("editMode", false);

        //
        String oldName = UserManager.getUsername(this);
        if (oldName != null && !oldName.isEmpty()) {
            editName.setText(oldName);
        }

        btnStart.setOnClickListener(v -> {

            String name = editName.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "İsim gir!", Toast.LENGTH_SHORT).show();
                return;
            }

            UserManager.saveUsername(this, name);


            finish();
        });
    }
}