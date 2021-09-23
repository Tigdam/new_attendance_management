package com.example.new_attendance_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentLogin extends AppCompatActivity {

    TextView Student_Login;
    EditText username;
    EditText editTextPassword;
    public Button loginbtn;
    String item="admin";
    String userid,pass;
    DatabaseReference mreference;
    ProgressDialog mDialog;
    private static final String FILE_NAME = "state.txt";
    String state;
    String falg=null,uuid=null;
    private static long back_pressed;
    private FirebaseAuth mAuth;
    private TextView forpass_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        username = findViewById(R.id.username_et);
        editTextPassword = findViewById(R.id.password_et);
        loginbtn= findViewById(R.id.loginButton);
        forpass_btn=findViewById(R.id.forgetpass_tv);

        mAuth = FirebaseAuth.getInstance();
        loginbtn.setOnClickListener(v -> {
            final String[] email = {username.getText().toString().trim()};
            String password = editTextPassword.getText().toString().trim();


            if(password.isEmpty()){
                editTextPassword.setError("Password is Required");
                editTextPassword.requestFocus();
                return;
            }
            if(email[0].isEmpty()){
                username.setError("Email is Required");
                username.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email[0]).matches()) {
                username.setError("Please provide valid email");
                username.requestFocus();
                return;
            }
            if(password.length()<6){
                editTextPassword.setError("Min password length should be 6 characters");
                editTextPassword.requestFocus();
                return;
            }



            mAuth.signInWithEmailAndPassword(email[0], password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                       /* FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        String uid = task.getResult().getUser().getUid();*/


                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        firebaseDatabase.getReference().child("AllStudent").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //int usertype = snapshot.getValue(Integer.class);



                                    Toast.makeText(StudentLogin.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(StudentLogin.this, student_dashbord.class));




                                //Toast.makeText(MainActivity.this, "Check your email to verify your email", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {


                            }
                        });

                        /*assert user != null;
                        if(user.isEmailVerified())
                        {
                            startActivity(new Intent(MainActivity.this, user_dashboard.class));
                        }
                        else
                        {
                            user.isEmailVerified();
                            Toast.makeText(MainActivity.this, "Check your email to verify your email", Toast.LENGTH_SHORT).show();
                        }*/




                    }
                    else {
                        Toast.makeText(StudentLogin.this, "Failed to login! Check your credentials", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        });





    }


}