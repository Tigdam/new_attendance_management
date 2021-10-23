package com.example.new_attendance_management;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class admin_view_LeaveApp extends AppCompatActivity {
    private RecyclerView recyclerView;
    private leaveAdaptor adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_leave_app);

        recyclerView= findViewById(R.id.levaeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()

                        .setQuery(FirebaseDatabase.getInstance().getReference().child("leaveApplication"),UserHelperClass.class)
                        .setLifecycleOwner(this)
                        .build();
        leaveAdaptor adapter= new leaveAdaptor(options,this);
        recyclerView.setAdapter(adapter);
    }
}