package com.example.a8book;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GradesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        String grades = getIntent().getStringExtra("grades");

        TextView textViewGrades = findViewById(R.id.textViewGrades);
        textViewGrades.setText(grades);
    }
}