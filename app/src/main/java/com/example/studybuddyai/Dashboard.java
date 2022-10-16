package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class Dashboard extends AppCompatActivity {

    //Variables for the AppBarLayout
    AppBarLayout dash_banner, dash_name;

    //Variables for the Buttons
    Button btn_aivy, btn_subjects, btn_settings;

    //Variables for the Animation
    Animation btn_1, btn_2, btn_3, dash_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Assignment of the Animations
        btn_1 = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim);
        btn_2 = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim2);
        btn_3 = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim3);
        dash_title = AnimationUtils.loadAnimation(this, R.anim.dashboard_title_anim);

        //Assigning the elements in the Variables
        btn_aivy = findViewById(R.id.dashboard_aivy);
        btn_subjects = findViewById(R.id.dashboard_subjects);
        btn_settings = findViewById(R.id.dashboard_settings);
        dash_banner = findViewById(R.id.appBarLayout);
        dash_name = findViewById(R.id.appBarLayout2);

        //Setting of the Animations
        btn_aivy.setAnimation(btn_1);
        btn_subjects.setAnimation(btn_2);
        btn_settings.setAnimation(btn_3);
        dash_name.setAnimation(dash_title);

        //Clicks
        btn_aivy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Aivy.class);
                startActivity(intent);
            }
        });
        btn_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, MySubjects.class);
                startActivity(intent);
            }
        });
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Settings.class);
                startActivity(intent);
            }
        });

    }

}