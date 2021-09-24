package com.example.new_attendance_management;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addNotice extends AppCompatActivity {
    private EditText t1,d1;
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        t1= findViewById(R.id.title);
        d1= findViewById(R.id.description);
        b1= findViewById(R.id.btn_save);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("Title",t1.getText().toString());
                map.put("Description",d1.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Notice").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("jfbvk","Oncomplete:");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("jfbvk","Onfailure" + e.toString());
                    }
                });
                Intent intent = new Intent(addNotice.this, NoticeList.class);
                startActivity(intent);

            }
        });

    }
}