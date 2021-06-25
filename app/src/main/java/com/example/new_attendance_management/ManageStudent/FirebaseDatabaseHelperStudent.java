package com.example.new_attendance_management.ManageStudent;

import androidx.annotation.NonNull;

import com.example.new_attendance_management.FirebaseDatabaseHelper;
import com.example.new_attendance_management.Student;
import com.example.new_attendance_management.batchdetails;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelperStudent {
    // reading data start
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceStudents;
    DatabaseReference batchdetails;
    DatabaseReference dbbatchname;
    private List<Student> students = new ArrayList<>();
    //reading data ended


    public interface DataStatus {

        // reading data start
        void DataIsLoaded(List<Student> students, List<String> keys);
        //reading data ended

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    //reading data start
    public FirebaseDatabaseHelperStudent() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceStudents = mDatabase.getReference("Student");
        batchdetails=FirebaseDatabase.getInstance().getReference("Batchdetails");
        dbbatchname=FirebaseDatabase.getInstance().getReference("BatchName");

    }

    public void readStudents(final DataStatus dataStatus, String Class) {
        mReferenceStudents = mDatabase.getReference("Student").child(Class);
        mReferenceStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                students.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {//DataSnapShot object contains key and value of specific node
                    keys.add(keyNode.getKey());
                    Student student = keyNode.getValue(Student.class);
                    students.add(student);
                }
                dataStatus.DataIsLoaded(students, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    //reading data ended


    public void updateStudent(String key, Student student, batchdetails batchs, final FirebaseDatabaseHelper.DataStatus dataStatus, String sClass, String sBatch) {
        mReferenceStudents.child(sClass).child(key).setValue(student)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
        batchdetails.child(sBatch).child(sClass).child(key).setValue(batchs)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteStudent(String key, final FirebaseDatabaseHelper.DataStatus dataStatus,String sClass) {
        mReferenceStudents.child(sClass).child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
