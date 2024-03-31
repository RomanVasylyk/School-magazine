package com.example.a8book;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentsAdapter.OnEditClickListener {
    private RecyclerView recyclerViewStudents;
    private StudentsAdapter adapter;
    private List<Student> students;
    private static final int REQUEST_EDIT_GRADE = 1;
    private static final int REQUEST_ADD_GRADE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewStudents = findViewById(R.id.recyclerViewStudents);
        recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));

        students = new ArrayList<>();
        students.add(new Student("John Smith", "95, 87, 92"));
        students.add(new Student("Emily Johnson", "88, 91, 90"));
        students.add(new Student("Michael Brown", "78, 85, 80"));
        students.add(new Student("Emma Davis", "92, 94, 91"));
        students.add(new Student("Daniel Wilson", "85, 89, 87"));
        students.add(new Student("Olivia Martinez", "75, 81, 79"));
        students.add(new Student("William Taylor", "90, 92, 89"));
        students.add(new Student("Sophia Anderson", "93, 95, 94"));
        students.add(new Student("James Thomas", "82, 86, 84"));
        students.add(new Student("Isabella Garcia", "94, 90, 92"));

        adapter = new StudentsAdapter(this, students);
        recyclerViewStudents.setAdapter(adapter);

        adapter.setEditClickListener(new StudentsAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(int position) {
                Student student = students.get(position);
                Intent intent = new Intent(MainActivity.this, EditGradesActivity.class);
                intent.putExtra("student_grades", student.getGrade());
                intent.putExtra("student_position", position);
                startActivityForResult(intent, REQUEST_EDIT_GRADE);
            }

            @Override
            public void onAddGradeClick(int position) {
                Intent intent = new Intent(MainActivity.this, AddGradeActivity.class);
                intent.putExtra("student_position", position);
                startActivityForResult(intent, REQUEST_ADD_GRADE);
            }
        });
    }


    @Override
    public void onEditClick(int position) {
        Student student = students.get(position);
        Intent intent = new Intent(this, EditGradesActivity.class);
        intent.putExtra("student_grades", student.getGrade());
        intent.putExtra("student_position", position);
        startActivityForResult(intent, REQUEST_EDIT_GRADE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_EDIT_GRADE) {
                int position = data.getIntExtra("student_position", -1);
                String updatedGrades = data.getStringExtra("updated_grades");
                if (position != -1 && !updatedGrades.isEmpty()) {
                    students.get(position).setGrade(updatedGrades);
                    adapter.notifyItemChanged(position);
                }
            } else if (requestCode == REQUEST_ADD_GRADE) {
                int position = data.getIntExtra("student_position", -1);
                String newGrade = data.getStringExtra("new_grade");
                if (position != -1 && !newGrade.isEmpty()) {
                    String currentGrades = students.get(position).getGrade();
                    currentGrades += ", " + newGrade;
                    students.get(position).setGrade(currentGrades);
                    adapter.notifyItemChanged(position);
                }
            }
        }
    }

    @Override
    public void onAddGradeClick(int position) {
        Intent intent = new Intent(this, AddGradeActivity.class);
        intent.putExtra("student_position", position);
        startActivityForResult(intent, REQUEST_ADD_GRADE);
    }
}
