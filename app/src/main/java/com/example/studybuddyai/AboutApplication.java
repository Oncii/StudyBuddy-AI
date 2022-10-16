package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Pair;

import com.google.android.material.appbar.AppBarLayout;

public class AboutApplication extends AppCompatActivity {

    //Variables for the Animation
    Animation about_loc, about_app;

    //Variables for the AppBarLayout
    AppBarLayout about_name;

    //Variables for the Imageview
    ImageView abt_logo;

    //Variables for the TextView
    TextView abt_loc, abt_app_name, abt_app_ver, abt_app;

    //Variables for the Buttons
    Button abt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);

        //Assignment of the Animations
        about_loc = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim);
        about_app = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);

        //Assigning the elements in the Variables
        about_name = findViewById(R.id.about_appBar);
        abt_logo = findViewById(R.id.about_logo);
        abt_loc = findViewById(R.id.about_location);
        abt_app_name = findViewById(R.id.about_app_name);
        abt_app_ver = findViewById(R.id.about_app_ver);
        abt_app = findViewById(R.id.about_application);
        abt_back = findViewById(R.id.about_back);

        //Setting of the Animations
        abt_loc.setAnimation(about_loc);
        abt_logo.setAnimation(about_app);
        abt_app_name.setAnimation(about_app);
        abt_app_ver.setAnimation(about_app);
        abt_app.setAnimation(about_app);
        abt_back.setAnimation(about_app);

        abt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutApplication.this, Settings.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(about_name, "set_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AboutApplication.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}