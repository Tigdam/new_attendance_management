package com.example.new_attendance_management.ManageStudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.new_attendance_management.R;
import com.example.new_attendance_management.UpdateDeleteFaculty;

public class ManageStudent extends AppCompatActivity {
    Button fymca,symca,tymca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);

        fymca=findViewById(R.id.fymca_btn);
        symca=findViewById(R.id.symca_btn);
        tymca=findViewById(R.id.tymca_btn);

        fymca.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         Intent intent=new Intent(getApplicationContext(), viewStudent.class);
                                         intent.putExtra("id","FYMCA");
                                         startActivity(intent);
                                     }
                                 }
        );
        symca.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         Intent intent=new Intent(getApplicationContext(), viewStudent.class);
                                         intent.putExtra("id","SYMCA");
                                         startActivity(intent);
                                     }
                                 }
        );
        tymca.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         Intent intent=new Intent(getApplicationContext(), viewStudent.class);
                                         intent.putExtra("id","TYMCA");
                                         startActivity(intent);
                                     }
                                 }
        );
    }
}