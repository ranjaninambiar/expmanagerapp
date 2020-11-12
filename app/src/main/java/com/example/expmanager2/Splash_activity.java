package com.example.expmanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_activity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=5000;
    ImageView image;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        image=findViewById(R.id.action_image);
        txt=findViewById(R.id.textView2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.animate);
        image.setAnimation(animation);
        txt.setAnimation(animation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public  void run(){
                Intent homeintent =new Intent(Splash_activity.this,Start_app.class);
                startActivity(homeintent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}


