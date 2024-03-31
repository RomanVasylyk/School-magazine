package com.example.a8book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    private List<Student> students;
    private Context context;
    private OnEditClickListener editClickListener;

    public StudentsAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    public interface OnEditClickListener {
        void onEditClick(int position);
        void onAddGradeClick(int position);
    }

    public void setEditClickListener(OnEditClickListener listener) {
        this.editClickListener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student student = students.get(position);
        holder.textViewName.setText(student.getName());
        holder.textViewGrade.setText(student.getGrade());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GradesActivity.class);
                intent.putExtra("grades", student.getGrade());
                context.startActivity(intent);
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editClickListener != null) {
                    editClickListener.onEditClick(position);
                }
            }
        });

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editClickListener != null) {
                    editClickListener.onAddGradeClick(position);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewGrade;
        Button editButton;
        Button addButton;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewGrade = itemView.findViewById(R.id.textViewGrade);
            editButton = itemView.findViewById(R.id.editButton);
            addButton = itemView.findViewById(R.id.addButton);
        }
    }
}
