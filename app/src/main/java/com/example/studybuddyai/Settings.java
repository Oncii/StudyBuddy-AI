package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class Settings extends AppCompatActivity {

    //Variables for the Animations
    Animation set_title, btn_edit, btn_about, btn_out;

    //Variables for the AppBarLayout
    AppBarLayout set_name;

    //Variables for the TextView
    TextView set_loc, set_app_ver;

    //Variables for the Buttons
    Button set_edit, set_about, set_out, set_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Assignment of the Animations
        btn_edit = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim);
        btn_about = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim2);
        btn_out = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim3);
        set_title = AnimationUtils.loadAnimation(this, R.anim.dashboard_title_anim);

        //Assigning the elements in the Variables
        set_name = findViewById(R.id.settings_appBar2);
        set_loc = findViewById(R.id.settings_location);
        set_app_ver = findViewById(R.id.settings_app_ver);
        set_edit = findViewById(R.id.settings_edit_btn);
        set_about = findViewById(R.id.settings_about_btn);
        set_out = findViewById(R.id.settings_logout_btn);
        set_back = findViewById(R.id.settings_back);

        //Setting of the Animations
        set_edit.setAnimation(btn_edit);
        set_about.setAnimation(btn_about);
        set_out.setAnimation(btn_out);
        set_name.setAnimation(set_title);
        set_loc.setAnimation(btn_edit);
        set_back.setAnimation(btn_out);

        //Clicks
        set_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, EditProfile.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(set_name, "set_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Settings.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
        set_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, AboutApplication.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(set_name, "set_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Settings.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
        set_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Dashboard.class);
                startActivity(intent);
            }
        });
        set_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, LoginSignup.class);
                startActivity(intent);
            }
        });
    }
}