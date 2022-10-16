package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginSignup extends AppCompatActivity {

    //Variables for the Animation
    Animation loginSignup_btn1_anim, loginSignup_btn2_anim;

    //Variables for the button
    Button loginSignup_btn1, loginSignup_btn2;

    //Variables for the Logo
    ImageView loginSignup_img;

    //Variables for the Text
    TextView loginSignup_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        //Animation Assignment
        loginSignup_btn1_anim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);
        loginSignup_btn2_anim = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);

        //Assigning the elements in the variables
        loginSignup_btn1 = findViewById(R.id.loginSignup_signup);
        loginSignup_btn2 = findViewById(R.id.loginSignup_login);
        loginSignup_img = findViewById(R.id.loginSignup_logo);
        loginSignup_txt = findViewById(R.id.loginSignup_text);

        //Setting of the animations
        loginSignup_btn1.setAnimation(loginSignup_btn1_anim);
        loginSignup_btn2.setAnimation(loginSignup_btn2_anim);

        //Login/Signup Clicks
        loginSignup_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSignup.this, SignUp.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(loginSignup_img, "splash_img");
                pairs[1] = new Pair<View, String>(loginSignup_txt, "splash_txt");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginSignup.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
        loginSignup_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSignup.this, Login.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(loginSignup_img, "splash_img");
                pairs[1] = new Pair<View, String>(loginSignup_txt, "splash_txt");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginSignup.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });


    }
}