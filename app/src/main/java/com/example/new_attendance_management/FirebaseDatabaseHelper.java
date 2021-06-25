package com.example.new_attendance_management;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    // reading data start
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceFaculties;
    private List<Faculty> faculties = new ArrayList<>();
    //reading data ended


    public interface DataStatus {

        // reading data start
        void DataIsLoaded(List<Faculty> faculties, List<String> keys);
        //reading data ended

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    //reading data start
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceFaculties = mDatabase.getReference("Faculty");

    }

    public void readFaculties(final DataStatus dataStatus) {
        // everytime you update,delete,insert data addValueEventListner execute onDataChange()
        mReferenceFaculties.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                faculties.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : snapshot.getChildren()) {//DataSnapShot object contains key and value of specific node
                    keys.add(keyNode.getKey());
                    Faculty faculty = keyNode.getValue(Faculty.class);
                    faculties.add(faculty);
                }
                dataStatus.DataIsLoaded(faculties, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    //reading data ended

    /*
      public void addSite(Sector sector,final DataStatus dataStatus)
      {
          String key= mReferenceSectors.push().getKey();
          mReferenceSectors.child(key).setValue(sector)
                  .addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                          dataStatus.DataIsInserted();
                      }
                  });
      }

  */
    public void updateFaculty(String key, Faculty faculty, final DataStatus dataStatus) {
        mReferenceFaculties.child(key).setValue(faculty)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }


    public void deleteFaculty(String key, final DataStatus dataStatus) {
        mReferenceFaculties.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });


    }
}


