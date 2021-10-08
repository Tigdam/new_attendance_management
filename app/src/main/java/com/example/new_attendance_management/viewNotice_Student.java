package com.example.new_attendance_management;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class viewNotice_Student extends AppCompatActivity {
    private RecyclerView recyclerView;
    private sAdaptor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice_student);

        recyclerView= findViewById(R.id.recyclestd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<nList> options =
                new FirebaseRecyclerOptions.Builder<nList>()

                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice"),nList.class)
                        .setLifecycleOwner(this)
                        .build();
        sAdaptor adapter= new sAdaptor(options,this);
        recyclerView.setAdapter(adapter);
    }
}