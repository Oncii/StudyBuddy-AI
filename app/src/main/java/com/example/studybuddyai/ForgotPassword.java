package com.example.studybuddyai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword extends AppCompatActivity {

    //Variables for Imageview
    ImageView for_logo;

    //Variables for TextView
    TextView for_title, for_details;

    //Variables for TextInputLayout
    TextInputLayout for_cellNo;

    //Variables for Buttons
    Button for_cancel, for_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Assigning the elements in the variables
        for_logo = findViewById(R.id.forgot_logo);
        for_title = findViewById(R.id.forgot_title);
        for_details = findViewById(R.id.forgot_details);
        for_cellNo = findViewById(R.id.forgot_cellNo);
        for_cancel = findViewById(R.id.forgot_cancel);
        for_submit = findViewById(R.id.forgot_submit);

        for_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this, Login.class);

                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(for_logo, "splash_img");
                pairs[1] = new Pair<View, String>(for_title, "splash_txt");
                pairs[2] = new Pair<View, String>(for_cellNo, "login_input");
                pairs[3] = new Pair<View, String>(for_cancel, "login_can");
                pairs[4] = new Pair<View, String>(for_submit, "login_log");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgotPassword.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}