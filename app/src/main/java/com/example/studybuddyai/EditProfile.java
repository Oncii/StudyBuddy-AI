package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.util.Pair;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputLayout;

public class EditProfile extends AppCompatActivity {

    //Variables for the Animations
    Animation text_input_edit, edit_buttons, edit_loca;

    //Variables for the AppBarLayout
    AppBarLayout edit_name;

    //Variables for the TextInputLayout
    TextInputLayout edit_user, edit_mail, edit_pass;

    //Variables for the TextView
    TextView edit_loc, user_field, mail_field, pass_field;

    //Variables for the Buttons
    Button edit_can, edit_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Assignment of the Animations
        text_input_edit = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);
        edit_buttons = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);
        edit_loca = AnimationUtils.loadAnimation(this, R.anim.dashboard_btn_anim);

        //Assigning the elements in the Variables
        edit_name = findViewById(R.id.edit_appBar1);
        edit_loc = findViewById(R.id.edit_location);
        edit_user = findViewById(R.id.edit_username);
        edit_mail = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_password);
        edit_can = findViewById(R.id.edit_cancel);
        edit_up = findViewById(R.id.edit_update);
        user_field = findViewById(R.id.username_field);
        mail_field = findViewById(R.id.email_field);
        pass_field = findViewById(R.id.password_field);

        //Setting of the Animations
        edit_loc.setAnimation(edit_loca);
        edit_user.setAnimation(text_input_edit);
        edit_mail.setAnimation(text_input_edit);
        edit_pass.setAnimation(text_input_edit);
        edit_can.setAnimation(edit_buttons);
        edit_up.setAnimation(edit_buttons);
        user_field.setAnimation(edit_loca);
        mail_field.setAnimation(edit_loca);
        pass_field.setAnimation(edit_loca);

        edit_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfile.this, Settings.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(edit_name, "set_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(EditProfile.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }
}