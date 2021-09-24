package com.example.new_attendance_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeList extends AppCompatActivity {
    private ImageView add;
    private RecyclerView recyclerView;
    private nAdaptor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        add= findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeList.this, addNotice.class);
                startActivity(intent);

            }
        });

        recyclerView= findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<nList> options =
                new FirebaseRecyclerOptions.Builder<nList>()

                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice"),nList.class)
                        .setLifecycleOwner(this)
                        .build();
        nAdaptor adapter= new nAdaptor(options,this);
        recyclerView.setAdapter(adapter);
    }
}