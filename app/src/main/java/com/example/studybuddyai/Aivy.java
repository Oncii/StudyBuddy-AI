package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.appbar.AppBarLayout;

public class Aivy extends AppCompatActivity {

    //Duration of the Chatbot Page
    private static int RETURN_SCREEN = 5000;

    //Variables for the Animation
    Animation aiv_title;

    //Variables for the AppBarLayout
    AppBarLayout aiv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aivy);

        //Assignment of the Animations
        aiv_title = AnimationUtils.loadAnimation(this, R.anim.dashboard_title_anim);

        //Assigning the elements in the Variables
        aiv_name = findViewById(R.id.aivy_appBar);

        //Setting of the Animations
        aiv_name.setAnimation(aiv_title);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Aivy.this, Dashboard.class);
                startActivity(intent);
            }
        }, RETURN_SCREEN);
    }
}