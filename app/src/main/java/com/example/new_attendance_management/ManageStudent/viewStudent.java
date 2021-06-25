package com.example.new_attendance_management.ManageStudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.new_attendance_management.R;
import com.example.new_attendance_management.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class viewStudent extends AppCompatActivity {
String id;
    private RecyclerView mRecyclerView;
    private FloatingActionButton addbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        id=getIntent().getStringExtra("id");
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        addbtn=findViewById(R.id.fab);
        findViewById(R.id.progress).setVisibility(View.VISIBLE);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), StudentModification.class);
                startActivity(i);
            }
        });
        new FirebaseDatabaseHelperStudent().readStudents(new FirebaseDatabaseHelperStudent.DataStatus() {
            @Override
            public void DataIsLoaded(List<Student> Student, List<String> keys) {
                findViewById(R.id.progress).setVisibility(View.GONE);
                new RecyclerView_config_Student().setConfig(mRecyclerView, getApplicationContext(), Student,keys);
            }


            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        },id);
    }
}