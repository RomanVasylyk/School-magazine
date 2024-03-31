package com.example.a8book;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddGradeActivity extends AppCompatActivity {

    private EditText editTextGrade;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        editTextGrade = findViewById(R.id.editTextGrade);
        addButton = findViewById(R.id.addButton);

        editTextGrade.setInputType(InputType.TYPE_CLASS_NUMBER);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String grade = editTextGrade.getText().toString().trim();
                if (!grade.isEmpty()) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("new_grade", grade);
                    resultIntent.putExtra("student_position", getIntent().getIntExtra("student_position", -1));
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

    }
}
