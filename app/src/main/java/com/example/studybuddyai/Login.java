package com.example.studybuddyai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.util.Pair;

public class Login extends AppCompatActivity {

    //Animations
    Animation text_Input, login_btn;

    //Variables for the Text Input Layouts
    TextInputLayout log_username, log_password;

    //Variables for the Buttons
    Button log_cancel, log_login, log_forgot;

    //Variables for the Logo
    ImageView log_img;

    //Variables for the Text
    TextView log_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Assignment for the animation
        text_Input = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);
        login_btn = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);

        //Assigning the elements in the variables
        log_img = findViewById(R.id.login_logo);
        log_txt = findViewById(R.id.login_text);
        log_username = findViewById(R.id.login_username);
        log_password = findViewById(R.id.login_password);

        log_cancel = findViewById(R.id.login_cancel);
        log_login = findViewById(R.id.login_login);
        log_forgot = findViewById(R.id.login_forgot_pass);

        //Setting of the animations
        log_username.setAnimation(text_Input);
        log_password.setAnimation(text_Input);
        log_cancel.setAnimation(login_btn);
        log_login.setAnimation(login_btn);
        log_forgot.setAnimation(login_btn);

        //Login/Cancel Clicks
        log_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, LoginSignup.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(log_img, "splash_img");
                pairs[1] = new Pair<View, String>(log_txt, "splash_txt");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
        log_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);

                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(log_img, "splash_img");
                pairs[1] = new Pair<View, String>(log_txt, "splash_txt");
                pairs[2] = new Pair<View, String>(log_username, "login_input");
                pairs[3] = new Pair<View, String>(log_cancel, "login_can");
                pairs[4] = new Pair<View, String>(log_login, "login_log");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }

    private Boolean validateUsername() {
        String val = log_username.getEditText().getText().toString();

        if(val.isEmpty()) {
            log_username.setError("This field is required");
            return false;
        }
        else {
            log_username.setError(null);
            log_username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = log_password.getEditText().getText().toString();

        if(val.isEmpty()) {
            log_password.setError("This field is required");
            return false;
        }
        else {
            log_password.setError(null);
            log_password.setErrorEnabled(false);
            return true;
        }
    }
    private boolean isConnected(Login login) {

        ConnectivityManager connectivityManager = (ConnectivityManager) login.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConnection != null && wifiConnection.isConnected()) || (mobileConnection != null && mobileConnection.isConnected())) {
            return true;
        }
        else {
            return false;
        }
    }

    public void loginUser(View view) {

        if(!isConnected(this)) {
            showCustomDialogue();
        }
        else if(!validateUsername() | !validatePassword()) {
            return;
        }
        else {
            isUser();
        }
    }
    private void showCustomDialogue() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setMessage("Please connect to the internet to Login!")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), LoginSignup.class));
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void isUser() {

        final String username_input = log_username.getEditText().getText().toString().trim();
        final String password_input = log_password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://studybuddy-ai-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");

        Query checkUser = reference.orderByChild("signup_username").equalTo(username_input);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {

                    log_username.setError(null);
                    log_username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(username_input).child("signup_password").getValue(String.class);

                    if(passwordFromDB.equals(password_input)) {

                        log_username.setError(null);
                        log_username.setErrorEnabled(false);

                        String usernameFromDB = snapshot.child(username_input).child("signup_username").getValue(String.class);
                        String emailFromDB = snapshot.child(username_input).child("signup_email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);

                        intent.putExtra("signup_username", usernameFromDB);
                        intent.putExtra("signup_email", emailFromDB);
                        intent.putExtra("signup_password", passwordFromDB);

                        startActivity(intent);
                    }
                    else {
                        log_password.setError("Incorrect password!");
                        log_password.requestFocus();
                    }
                }
                else {
                    log_username.setError("No user exists in the database!");
                    log_username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}