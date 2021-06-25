package com.example.new_attendance_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.new_attendance_management.R;

import java.util.List;

public class UpdateDeleteFaculty extends AppCompatActivity {

    private EditText txt_Fname,txt_Fid;
    private Button btn_Fupdate;
    private TextView txt_Ftype,txt_Fback,txt_Fdelete;

    private String key;
    private String tname;
    private String tid;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_faculty);

        key=getIntent().getStringExtra("key");
        tname=getIntent().getStringExtra("tname");
        tid=getIntent().getStringExtra("tid");
        type=getIntent().getStringExtra("type");


        txt_Fname=(EditText) findViewById(R.id.facname_et);
        txt_Fname.setText(tname);

        txt_Fid=(EditText) findViewById(R.id.facemail_et);
        txt_Fid.setText(tid);

        txt_Ftype=(TextView) findViewById(R.id.factype);
        txt_Ftype.setText(type);


        btn_Fupdate=(Button)findViewById(R.id.facupdate_btn);
        txt_Fback=(TextView)findViewById(R.id.facback_btn);
        txt_Fdelete=(TextView)findViewById(R.id.facdelete_btn);



        btn_Fupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Faculty faculty=new Faculty();
                faculty.setTname(txt_Fname.getText().toString());
                faculty.setTid(txt_Fid.getText().toString());
                faculty.setType(txt_Ftype.getText().toString());

                new FirebaseDatabaseHelper().updateFaculty(key,faculty, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Faculty> faculties, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(UpdateDeleteFaculty.this,"Faculty Details Updated Successfully",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        txt_Fdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteFaculty(key, new FirebaseDatabaseHelper.DataStatus() {
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
                        Toast.makeText(UpdateDeleteFaculty.this,"Faculty Details Deleted Successfully",Toast.LENGTH_LONG).show();
                        finish();return;

                    }
                });
            }
        });

        txt_Fback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });

    }
}