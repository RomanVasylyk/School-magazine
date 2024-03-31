package com.example.a8book;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditGradesActivity extends AppCompatActivity {

    private EditText editTextGrades;
    private Button saveButton;
    private int studentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grades);

        editTextGrades = findViewById(R.id.editTextGrades);
        saveButton = findViewById(R.id.saveButton);

        String studentGrades = getIntent().getStringExtra("student_grades");
        editTextGrades.setText(studentGrades);

        studentPosition = getIntent().getIntExtra("student_position", -1);

        editTextGrades.setFilters(new InputFilter[] {new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isDigit(source.charAt(i)) && source.charAt(i) != ',') {
                        Toast.makeText(EditGradesActivity.this, "Only numbers and commas can be entered", Toast.LENGTH_SHORT).show();
                        return "";
                    }
                }
                return null;
            }
        }});

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedGrades = editTextGrades.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updated_grades", updatedGrades);
                resultIntent.putExtra("student_position", studentPosition);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
