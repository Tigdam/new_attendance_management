package com.example.new_attendance_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addHoliday extends AppCompatActivity {

    EditText holidayTo ,holidayFrom, holidayDes;
    Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holiday);

        holidayTo=(EditText)findViewById(R.id.editTextTextPersonName);
        holidayFrom=(EditText)findViewById(R.id.editTextTextPersonName2);
        holidayDes=(EditText)findViewById(R.id.editTextTextPersonName3);


        back=(Button)findViewById(R.id.button2);
        back.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

        submit=(Button)findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert()
    {
        Map<String,Object> map=new HashMap<>();
        map.put("HolidayTo",holidayTo.getText().toString());
        map.put("HolidayFrom",holidayFrom.getText().toString());
        map.put("HolidayDes",holidayDes.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        holidayTo.setText("");
                        holidayFrom.setText("");
                        holidayDes.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
}