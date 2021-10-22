package com.example.new_attendance_management;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Timetable extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Intent intent=getIntent();
        String cls=intent.getStringExtra("mca");


        recyclerView= findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<Tlist> options =
                new FirebaseRecyclerOptions.Builder<Tlist>()

                        .setQuery(FirebaseDatabase.getInstance().getReference().child(cls), Tlist.class)
                        .setLifecycleOwner(this)
                        .build();
        Tadaptor adapter= new Tadaptor(options,cls,this);
        recyclerView.setAdapter(adapter);
    }



}

