package com.example.new_attendance_management;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class addHoliday extends AppCompatActivity {

    EditText  holidayDes;
    TextView holidayTo ,holidayFrom;
    Button submit,back;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_holiday);

        holidayTo= findViewById(R.id.dateto);
        holidayFrom= findViewById(R.id.datefrom);
        holidayDes= findViewById(R.id.description);

        holidayTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == holidayTo) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(addHoliday.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    holidayTo.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                    datePickerDialog.show();
                }

            }
        });

        holidayFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == holidayFrom) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(addHoliday.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    holidayFrom.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                    datePickerDialog.show();
                }




            }
        });





        submit= findViewById(R.id.btn_save);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,Object> map=new HashMap<>();
                map.put("HolidayTo",holidayTo.getText().toString());
                map.put("HolidayFrom",holidayFrom.getText().toString());
                map.put("HolidayDes",holidayDes.getText().toString());

                String hd = holidayDes.getText().toString();
                String ht = holidayDes.getText().toString();
                String hf = holidayDes.getText().toString();
                if(hd.isEmpty()){
                    holidayDes.setError("Description is required");
                    holidayDes.requestFocus();
                    return;
                }
                if(ht.isEmpty()) {
                    holidayTo.setError("To Date is required");
                    holidayTo.requestFocus();
                }


                FirebaseDatabase.getInstance().getReference().child("Holidays").push()
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

                Intent intent = new Intent(addHoliday.this, disp_holiday.class);
                startActivity(intent);
            }
        });


    }


}