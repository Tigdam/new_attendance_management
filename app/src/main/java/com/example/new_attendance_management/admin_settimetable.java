package com.example.new_attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_settimetable extends AppCompatActivity {
    private Button fy,sy,ty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settimetable);
        fy=(Button) findViewById(R.id.btn_fymca);
        sy=(Button) findViewById(R.id.btn_symca);
        ty=(Button)findViewById(R.id.btn_tymca);
        fy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fy="FYMCA";
                Intent intent = new Intent(admin_settimetable.this,Timetable.class);
                intent.putExtra("mca",fy);
                startActivity(intent);


            }
        });
        sy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sy="SYMCA";
                Intent intent = new Intent(admin_settimetable.this,Timetable.class);
                intent.putExtra("mca",sy);
                startActivity(intent);

            }
        });
        ty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ty="TYMCA";
                Intent intent = new Intent(admin_settimetable.this,Timetable.class);
                intent.putExtra("mca",ty);
                startActivity(intent);
            }
        });
    }
}