package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        ImageView img = findViewById(R.id.imageView);
        TextView t = findViewById(R.id.textView);

        Animation ianim = AnimationUtils.loadAnimation(this,R.anim.logoanim);
        Animation tanim = AnimationUtils.loadAnimation(this,R.anim.textanim);

        img.setAnimation(ianim);
        t.setAnimation(tanim);

        Intent i = new Intent(SplashscreenActivity.this,LoginActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        },3000);
    }
}