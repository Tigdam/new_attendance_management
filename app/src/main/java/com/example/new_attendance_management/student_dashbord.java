package com.example.new_attendance_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class student_dashbord extends AppCompatActivity {

    ImageView Hoilday_imageView, Profile_imageView,timetable_imageView,Notice_imageView,Leave_imageView,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashbord);

        Hoilday_imageView = findViewById(R.id.v_pubholiday);
        timetable_imageView = findViewById(R.id.v_Timetable);
        Notice_imageView = findViewById(R.id.v_notice);
        Leave_imageView = findViewById(R.id.v_leave);
        exit = findViewById(R.id.imageView);


        Hoilday_imageView.setOnClickListener(v -> {
            startActivity(new Intent(student_dashbord.this, viewHoilday_Student.class));
        });

        timetable_imageView.setOnClickListener(v -> {
            startActivity(new Intent(student_dashbord.this, student_timetable_home.class));
        });

        Notice_imageView.setOnClickListener(v -> {
            startActivity(new Intent(student_dashbord.this, viewNotice_Student.class));
        });

        Leave_imageView.setOnClickListener(v -> {
            startActivity(new Intent(student_dashbord.this, viewLeave_Student.class));
        });

        exit.setOnClickListener(v -> {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(student_dashbord.this, MainActivity.class));
        });




    }
}