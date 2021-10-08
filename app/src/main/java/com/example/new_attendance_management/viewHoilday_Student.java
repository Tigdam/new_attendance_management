
package com.example.new_attendance_management;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class viewHoilday_Student extends AppCompatActivity {

    RecyclerView recyclerView;
    hAdaptor adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hoilday_student);

        recyclerView = (RecyclerView) findViewById(R.id.recycleStudent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Holidays"), model.class)
                            .build();

            adapter = new hAdaptor(options);
        recyclerView.setAdapter(adapter);


    }
   @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
            super.onStop();
            adapter.stopListening();
        }

}