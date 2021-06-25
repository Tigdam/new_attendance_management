package com.example.new_attendance_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.new_attendance_management.R;

import java.util.List;

public class viewFaculty extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button addbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty);
        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview_faculty);
        addbtn=(Button)findViewById(R.id.btn_addfaculty);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addfaculty.class);
                startActivity(i);
            }
        });
        new FirebaseDatabaseHelper().readFaculties(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Faculty> Faculty, List<String> keys) {
                findViewById(R.id.progress).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView,viewFaculty.this, Faculty,keys);
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
        });
    }
}