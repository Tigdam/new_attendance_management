package com.example.new_attendance_management.ManageStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.new_attendance_management.Faculty;
import com.example.new_attendance_management.FirebaseDatabaseHelper;
import com.example.new_attendance_management.R;
import com.example.new_attendance_management.Student;
import com.example.new_attendance_management.UpdateDeleteFaculty;
import com.example.new_attendance_management.batchdetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentModification extends AppCompatActivity {

    String item_batchname,item_classname;
    EditText StnName,StnEmail,StnEnrollmentNo;
    Button addStudent,updateStudent,deleteStudent,backStudent;
    String password;
    DatabaseReference databaseStudent;
    DatabaseReference batchdetails;
    DatabaseReference dbbatchname;
    FirebaseAuth mAuth;
    ProgressDialog mProgress;
    String sname,semail,srollno,sbatch,sclass,key,stnname,stnemail,stnrollno,stnclass,stnbatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_modification);
        mProgress=new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();
        databaseStudent= FirebaseDatabase.getInstance().getReference("Student");
        batchdetails=FirebaseDatabase.getInstance().getReference("Batchdetails");
        dbbatchname=FirebaseDatabase.getInstance().getReference("BatchName");
        StnName=findViewById(R.id.stdname_et);
        StnEmail=findViewById(R.id.stdemail_et);
        StnEnrollmentNo=findViewById(R.id.enrollnemtno_et);
        StnEmail=findViewById(R.id.stdemail_et);
        addStudent=findViewById(R.id.addStudent_btn);
        updateStudent=findViewById(R.id.stnupdate_btn);
        backStudent=findViewById(R.id.stnback_btn);
        deleteStudent=findViewById(R.id.stndelete_btn);
        mProgress.setMessage("Please wait...");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.show();
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        //Spinner for batchname
        final List<String> lstbacthn=new ArrayList<String>();

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        //Spinner for classname
        final List<String> selectedclass=new ArrayList<String>();
        Intent intent1 = getIntent();
        if(intent1.hasExtra("key")) {
            key = getIntent().getStringExtra("key");
            stnname = getIntent().getStringExtra("sname");
            stnrollno = getIntent().getStringExtra("srollno");
            stnemail = getIntent().getStringExtra("semail");
            stnclass = getIntent().getStringExtra("sclass");
            stnbatch = getIntent().getStringExtra("sbatch");
            addStudent.setVisibility(View.GONE);
            updateStudent.setVisibility(View.VISIBLE);
            deleteStudent.setVisibility(View.VISIBLE);
            backStudent.setVisibility(View.VISIBLE);
            StnName.setText(stnname);
            StnEmail.setText(stnemail);
            StnEnrollmentNo.setText(stnrollno);
            lstbacthn.add(stnbatch);
            selectedclass.add(stnclass);
            mProgress.dismiss();
        } else{
            addStudent.setVisibility(View.VISIBLE);
            updateStudent.setVisibility(View.GONE);
            deleteStudent.setVisibility(View.GONE);
            backStudent.setVisibility(View.GONE);
            lstbacthn.add("Select Batch");
            dbbatchname.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dsp :dataSnapshot.getChildren()){
                        String name;
                        name=dsp.getKey();
                        lstbacthn.add(name);
                        mProgress.dismiss();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    mProgress.dismiss();
                    Toast.makeText(getApplicationContext(), "Batch loading Failed...", Toast.LENGTH_LONG).show();
                }
            });

            selectedclass.add("Select Class");
        }

        ArrayAdapter<String> batcharrayadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lstbacthn);
        batcharrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(batcharrayadapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_batchname=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        selectedclass.add("FYMCA");
        selectedclass.add("SYMCA");
        selectedclass.add("TYMCA");
        ArrayAdapter<String> classarrayadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,selectedclass);
        classarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(classarrayadapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_classname=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stdstartadd();
            }
        });

        updateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student=new Student();
                student.setSname(StnName.getText().toString());
                student.setSrollno(StnEnrollmentNo.getText().toString());
                student.setSclass(item_batchname.toUpperCase().trim());
                student.setSbatch(item_classname.toUpperCase().trim());
                student.setSemail(StnEmail.getText().toString());

                batchdetails batchs = new batchdetails(StnEnrollmentNo.getText().toString(), StnName.getText().toString());

                new FirebaseDatabaseHelperStudent().updateStudent(key,student,batchs, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Faculty> faculties, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(getApplicationContext(),"Student Details Updated Successfully",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                },item_classname,item_batchname);
            }
        });

        deleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelperStudent().deleteStudent(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Faculty> faculties, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(getApplicationContext(),"student Details Deleted Successfully",Toast.LENGTH_LONG).show();
                        finish();
                        return;

                    }
                },item_classname);
            }
        });

        backStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });

    }
    public void stdstartadd(){
        sname=StnName.getText().toString().trim();
        semail=StnEmail.getText().toString().trim();
        srollno=StnEnrollmentNo.getText().toString().trim();
        sbatch=item_batchname.toUpperCase().trim();
        sclass=item_classname.toUpperCase().trim();

        if (sname.isEmpty())
        {
            StnName.setError("Enter name");
            return;
        }
        else if (semail.isEmpty()){
            StnEmail.setError("Enter email");
            return;
        }
        else if (srollno.isEmpty()){
            StnEnrollmentNo.setError("Enter Valid Number");
            return;
        }
        else if (!sclass.equals("SELECT CLASS") && !sbatch.equals("SELECT BATCH")) {
            mProgress.setTitle("Registering...");
            mProgress.setMessage("Please wait...");
            mProgress.setCanceledOnTouchOutside(false);
            mProgress.show();
            password = sclass + srollno;
            mAuth.createUserWithEmailAndPassword(semail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        com.example.new_attendance_management.batchdetails batchs = new batchdetails(srollno, sname);
                        batchdetails.child(sbatch).child(sclass).child(srollno).setValue(batchs).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Student student = new Student(sname, semail, sclass, sbatch, password, srollno);
                                    databaseStudent.child(sclass).child(srollno).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                mProgress.dismiss();
                                                startActivity(getIntent());
                                                Toast.makeText(getApplicationContext(), "Student Added Successfully", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                mProgress.dismiss();
                                                Toast.makeText(getApplicationContext(), "Failed...", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                                } else {
                                    mProgress.dismiss();
                                    Toast.makeText(getApplicationContext(), "Failed...", Toast.LENGTH_LONG).show();

                                }
                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), "Already Register This E-Mail", Toast.LENGTH_SHORT).show();
                        mProgress.dismiss();
                    }
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), "All Filed are Required!", Toast.LENGTH_SHORT).show();
        }
    }
}