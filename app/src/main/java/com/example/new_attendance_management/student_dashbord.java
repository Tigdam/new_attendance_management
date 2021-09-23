package com.example.new_attendance_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class student_dashbord extends AppCompatActivity {

    ImageView Hoilday_imageView, Profile_imageView,Attendance_imageView,Notice_imageView,Leave_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashbord);

        Hoilday_imageView = findViewById(R.id.v_pubholiday);
        Profile_imageView = findViewById(R.id.v_profile);
        Attendance_imageView = findViewById(R.id.v_attendence);
        Notice_imageView = findViewById(R.id.v_notice);
        Leave_imageView = findViewById(R.id.v_leave);


        Hoilday_imageView.setOnClickListener(v -> {
            startActivity(new Intent(student_dashbord.this, viewHoilday_Student.class));
        });

        Notice_imageView.setOnClickListener(v -> {
            startActivity(new Intent(student_dashbord.this, viewNotice_Student.class));
        });

        Leave_imageView.setOnClickListener(v -> {
            startActivity(new Intent(student_dashbord.this, viewLeave_Student.class));
        });




    }
}