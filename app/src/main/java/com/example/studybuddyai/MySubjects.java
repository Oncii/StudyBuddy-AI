package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;

public class MySubjects extends AppCompatActivity {

    //Variables for the Animation
    Animation sub_title;

    //Variables for the AppBarLayout
    AppBarLayout sub_name;

    //Variables for the Buttons
    Button sub_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subjects);

        //Assignment of the Animations
        sub_title = AnimationUtils.loadAnimation(this, R.anim.dashboard_title_anim);

        //Assigning the elements in the Variables
        sub_name = findViewById(R.id.sub_appBar);
        sub_back = findViewById(R.id.subjects_back);

        //Setting of the Animations
        sub_name.setAnimation(sub_title);

        sub_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MySubjects.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}