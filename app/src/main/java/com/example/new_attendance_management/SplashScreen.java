package com.example.new_attendance_management;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    boolean a;
    TextView text,text1;
    private static int SPLASH_SCREEN=3000;

    ImageView iv;
    Animation fade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        a=isNetworkAvailable();
        iv=(ImageView)findViewById(R.id.logo);
        text=(TextView)findViewById(R.id.tv);
        text1=(TextView)findViewById(R.id.bel_tv);
        fade= AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        iv.startAnimation(fade);
        fade.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (a==true){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i=new Intent(SplashScreen.this, MainActivity.class);
                            Pair[] pairs= new Pair[1];
                            pairs[0]=new Pair<View, String>(iv,"logo_image");
                            //pairs[1]=new Pair<View,String>(text,"logo_text");

                            ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                            startActivity(i,options.toBundle());
                            finish();

                        }
                    },SPLASH_SCREEN);
                }
                else {
                    text.setText("Internet Unavailable !");
                    text1.setText("Please connect to Internet");
                    Toast.makeText(SplashScreen.this,"Please Connect to Internet", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=isNetworkAvailable();
                if (a==true){
                    finish();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
                else {
                    text.setText("Internet Unavailable !");
                    Toast.makeText(SplashScreen.this,"Please Connect to Internet", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}

