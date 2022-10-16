package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.util.Pair;

public class Splashscreen extends AppCompatActivity {

    //Duration of the Splashscreen
    private static int SPLASH_SCREEN = 2000;

    //Variables for the Animation
    Animation logo_anim, text_anim;

    //Variables for the Logo
    ImageView splash_logo;

    //Variables for the Text
    TextView splash_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        //Animation Assignment
        logo_anim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_logo_anim);
        text_anim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);

        //Assigning the elements in the variables
        splash_logo = findViewById(R.id.splashscreen_logo);
        splash_text = findViewById(R.id.splashscreen_text);

        //Setting of the animations
        splash_logo.setAnimation(logo_anim);
        splash_text.setAnimation(text_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, LoginSignup.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(splash_logo, "splash_img");
                pairs[1] = new Pair<View, String>(splash_text, "splash_txt");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splashscreen.this, pairs);
                startActivity(intent, options.toBundle());
            }
        }, SPLASH_SCREEN);
    }
}