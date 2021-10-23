package com.example.new_attendance_management;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

public class viewLeave_Student extends AppCompatActivity {
    EditText selectDate,selectTime;
    private int mYear, mMonth, mDay;
    Button button, pdfup;
    //mHour, mMinute;
    EditText leave_title, description, type_of_leave,nametxt;
    TextView attach_file;

    private final int PICK_PDF_CODE = 2342;


    StorageReference storageReference;
    FirebaseDatabase rootNode;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_leave_student);

        selectTime=(EditText)findViewById(R.id.datefrom);
        selectDate=(EditText)findViewById(R.id.to);
        leave_title = findViewById(R.id.TitleLeave);
        description = findViewById(R.id.description);
        type_of_leave = findViewById(R.id.TypeOfLeave);
        attach_file = findViewById(R.id.attach_file);

        nametxt = findViewById(R.id.TitleNameLeave);

        button =  findViewById(R.id.btn);
        pdfup = findViewById(R.id.pdf_up);

        storageReference = FirebaseStorage.getInstance().getReference();
        rootNode = FirebaseDatabase.getInstance();

        databaseReference = rootNode.getReference("leaveApplication");

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == selectDate) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(viewLeave_Student.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    selectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                    datePickerDialog.show();
                }




            }
        });

        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == selectTime) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(viewLeave_Student.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    selectTime.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                    datePickerDialog.show();
                }




            }
        });


        pdfup.setEnabled(false);
        attach_file.setOnClickListener(v -> selectPDF());

        button.setOnClickListener(v -> firebase());

    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECTED"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            pdfup.setEnabled(true);
            attach_file.setText(data.getDataString()
                    .substring(data.getDataString().lastIndexOf("/") + 1));

            pdfup.setOnClickListener(v -> uploadePDFFileFirebase(data.getData()));

        }
    }

    private void uploadePDFFileFirebase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("File is Loading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("upload"+System.currentTimeMillis()+ ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(taskSnapshot -> {

                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while(!uriTask.isComplete());
                    Uri uri = uriTask.getResult();

                    UserHelperClass putPDF = new UserHelperClass(attach_file.getText().toString(), uri.toString());
                    databaseReference.child(databaseReference.push().getKey()).setValue(putPDF);
                    Toast.makeText(viewLeave_Student.this, "File Upload", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }).addOnProgressListener(snapshot -> {

            double progress = (100.0* snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
            progressDialog.setMessage("File Uploaded..." +(int) progress+ "%");

        });
    }


    private void firebase() {

        String n=nametxt.getText().toString();
        String fromDate = selectTime.getText().toString();
        String toDate = selectDate.getText().toString();
        String title = leave_title.getText().toString();
        String des = description.getText().toString();
        String typ_of_leave = type_of_leave.getText().toString();



        if(n.isEmpty()){
            nametxt.setError("Name is required");
            nametxt.requestFocus();
            return;
        }

        if(title.isEmpty()){
            leave_title.setError("Title is required");
            leave_title.requestFocus();
            return;
        }
        if(des.isEmpty()){
            description.setError("Description is required");
            description.requestFocus();
            return;
        }
        if(typ_of_leave.isEmpty()){
            type_of_leave.setError("Leave Type is required");
            type_of_leave.requestFocus();
            return;
        }




        if (fromDate.compareTo(toDate)>0)
        {
            Toast.makeText(viewLeave_Student.this, "select proper date",Toast.LENGTH_SHORT).show();
            return ;
        }

        if (fromDate.equals(toDate))
        {
            Toast.makeText(viewLeave_Student.this, "same date",Toast.LENGTH_SHORT).show();
            return ;
        }



        UserHelperClass helperClass = new UserHelperClass(n,title, des, typ_of_leave, fromDate, toDate);
        databaseReference.child(n).setValue(helperClass);

        Toast.makeText(viewLeave_Student.this, "Successfully Added ", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(viewLeave_Student.this, student_dashbord.class));
    }

}