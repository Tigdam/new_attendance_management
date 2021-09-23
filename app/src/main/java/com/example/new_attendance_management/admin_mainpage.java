package com.example.new_attendance_management;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.new_attendance_management.ManageStudent.ManageStudent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class admin_mainpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String tid;
    LinearLayout addstubtn,addfacbtn,addsubbtn,addholdy,settimetable,viewleave;
    CircleImageView admin_nav_image;

    private TextView nav_name,name_tv;
    private TextView nav_username,username_tv;

    DatabaseReference facultyrec;
    FirebaseAuth mAuth;
    View headerViw1;
    ProgressDialog mDialog;
    private static final String FILE_NAME = "state.txt";

    ProgressBar progressBar;

    FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE=123;
    Uri imagepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mainpage);
        Intent intent=getIntent();
        tid=intent.getStringExtra("tid");

        firebaseStorage=FirebaseStorage.getInstance();

        mDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        name_tv=findViewById(R.id.name_tv);
        username_tv=findViewById(R.id.username_tv);
        addfacbtn=findViewById(R.id.addfaculty_btn);
        //remfacbtn=findViewById(R.id.removefaculty_btn);
        addstubtn=findViewById(R.id.addstudent_btn);
        //remstubtn=findViewById(R.id.removestudent_btn);
        addsubbtn=findViewById(R.id.addsbject_btn);
        //baclogreg=findViewById(R.id.baclog_regby_admin_btn);

        addholdy = findViewById(R.id.addholiday_btn);
        viewleave =findViewById(R.id.viewleave_btn);
        settimetable = findViewById(R.id.settimetable_btn);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewleave.setOnClickListener(v -> {
            startActivity(new Intent(admin_mainpage.this, viewLeaveApplication_ADMIN.class));
        });

        settimetable.setOnClickListener(v -> {
            startActivity(new Intent(admin_mainpage.this, admin_settimetable.class));
        });


       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */
        facultyrec= FirebaseDatabase.getInstance().getReference("Faculty");
        facultyrec.keepSynced(true);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerViw1=navigationView.getHeaderView(0);
        nav_name=(TextView)headerViw1.findViewById(R.id.nav_name_tv);
        nav_username=(TextView)headerViw1.findViewById(R.id.nav_username_tv);
        admin_nav_image=(CircleImageView)headerViw1.findViewById(R.id.nav_admin_imageView);
        navigationView.setNavigationItemSelectedListener(this);



        facultyrec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String musernam,mname,musertype;
                mname=dataSnapshot.child(tid).child("tname").getValue(String.class);
                nav_name.setText(mname);
                name_tv.setText(mname);

                musernam=tid;

                musernam=musernam+"@gmail.com";
                nav_username.setText(musernam);
                musertype=dataSnapshot.child(tid).child("type").getValue(String.class);
                username_tv.setText(musertype);
                progressBar.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addfacbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),viewFaculty.class));
            }
        });
/*
        remfacbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),removefaculty.class));

            }
        });*/

        addstubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ManageStudent.class));

            }
        });
/*
        remstubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),removestudent.class));

            }
        });*/
        addsubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),addsubjectbyadmin.class));
            }
        });

        addholdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),disp_holiday.class));
            }
        });
/*
        baclogreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),baclog_reg_by_admin.class));
            }
        });

        admin_nav_image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent=new Intent(getApplicationContext(),facultyprofile.class);
                intent.putExtra("tid",tid);
                startActivity(intent);
                return false;
            }
        });*/


        //naviagtion bar image

        StorageReference storageReference1=firebaseStorage.getReference("Faculty");
        storageReference1.child(tid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    Picasso.get().load(uri).fit().centerCrop().into(admin_nav_image);
                }catch (Exception e){}
            }
        });


        //end nav_bar Image



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert!");
            builder.setMessage("Do you want to Exit?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    finish();
                    ActivityCompat.finishAffinity(admin_mainpage.this);
                    System.exit(0);
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();  // Show the Alert Dialog box
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_mainpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),about_app.class));
            return true;
        }
        else if(id == R.id.logout_title_btn) {
            signout("599");
            Intent logout=new Intent(admin_mainpage.this,MainActivity.class);
            logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(logout);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pass_reset) {
            forPassCustomDialog();

        }
        else if (id == R.id.nav_admin_profile) {
            Intent intent=new Intent(this,facultyprofile.class);
            intent.putExtra("tid",tid);
            startActivity(intent);
        }
        else if (id == R.id.nav_logout_btn) {
            signout("599");
            Intent logout=new Intent(admin_mainpage.this,MainActivity.class);
            logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(logout);
        } else if (id == R.id.nav_aboutapp) {
            startActivity(new Intent(getApplicationContext(),about_app.class));
        }
        else if (id == R.id.nav_coollective_batch_create) {
            startActivity(new Intent(getApplicationContext(),collective_batch_creation_mid1.class));
        }
        else if (id == R.id.nav_batch_name_cr_btn) {
            startActivity(new Intent(getApplicationContext(),batchnamecreate.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void forPassCustomDialog()
    {
        final AlertDialog.Builder myDialog =new AlertDialog.Builder(admin_mainpage.this);
        LayoutInflater inflater =LayoutInflater.from(admin_mainpage.this);

        final View myview=inflater.inflate(R.layout.forgot_pass,null);

        final AlertDialog dialog=myDialog.create();
        dialog.setView(myview);

        final EditText forpassEmail=myview.findViewById(R.id.forpassemail_et);
        Button resetpass_btn=myview.findViewById(R.id.forpass_bt);

        resetpass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail=forpassEmail.getText().toString().trim();
                if(TextUtils.isEmpty(mEmail))
                {
                    forpassEmail.setError("Please Enter Valid Email..!");
                    return;
                }

                mDialog.setMessage("Sending Reset Link...");
                mDialog.show();
                mAuth.sendPasswordResetEmail(mEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            dialog.dismiss();
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Reset link sent to your Email..!", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Error...in sending Password Reset Link", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        dialog.show();
    }


    public void signout(String statevar){

        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(statevar.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}
