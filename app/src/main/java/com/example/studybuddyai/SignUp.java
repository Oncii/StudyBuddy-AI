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
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    //Animations
    Animation textInput, signUp_btn;

    //Variables for the Text Input Layout
    TextInputLayout sign_username, sign_email, sign_password, sign_conf_pass, sign_cellNo;

    //Variables for Buttons
    Button sign_cancel, sign_signUp;

    //Variables for the Logo
    ImageView sign_img;

    //Variables for the Text
    TextView sign_txt;

    //Firebase Database
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Assignment for the animation
        textInput = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);
        signUp_btn = AnimationUtils.loadAnimation(this, R.anim.splashscreen_text_anim);

        //Assigning the elements in the variables
        sign_img = findViewById(R.id.signUp_logo);
        sign_txt = findViewById(R.id.signUp_text);
        sign_username = findViewById(R.id.signUp_username);
        sign_email = findViewById(R.id.signUp_email);
        sign_cellNo = findViewById(R.id.signUp_cellNo);
        sign_password = findViewById(R.id.signUp_password);
        sign_conf_pass = findViewById(R.id.signUp_conf_password);

        sign_cancel = findViewById(R.id.signUp_cancel);
        sign_signUp = findViewById(R.id.signUp_signUp);

        //Setting of the animations
        sign_username.setAnimation(textInput);
        sign_email.setAnimation(textInput);
        sign_cellNo.setAnimation(textInput);
        sign_password.setAnimation(textInput);
        sign_conf_pass.setAnimation(textInput);
        sign_cancel.setAnimation(signUp_btn);
        sign_signUp.setAnimation(signUp_btn);

        //Sign Up/Cancel Clicks
        sign_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, LoginSignup.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(sign_img, "splash_img");
                pairs[1] = new Pair<View, String>(sign_txt, "splash_txt");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });

    }

    private Boolean validateUsername() {
        String val = sign_username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()) {
            sign_username.setError("This field is required");
            return false;
        } else if(val.length()>=15) {
            sign_username.setError("Username too long!");
            return false;
        }
        else if(!val.matches(noWhiteSpace)) {
            sign_username.setError("Whites spaces not allowed!");
            return false;
        }
        else {
            sign_username.setError(null);
            sign_username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val = sign_email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            sign_email.setError("This field is required");
            return false;
        } else if(!val.matches(emailPattern)) {
            sign_email.setError("Invalid Email Address!");
            return false;
        }
        else {
            sign_email.setError(null);
            sign_email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatecellNo() {
        String val = sign_cellNo.getEditText().getText().toString();

        if(val.isEmpty()) {
            sign_cellNo.setError("This field is required");
            return false;
        }
        else {
            sign_cellNo.setError(null);
            sign_cellNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = sign_password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#_$%^&.+=-])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(val.isEmpty()) {
            sign_password.setError("This field is required");
            return false;
        } else if(!val.matches(passwordVal)) {
            sign_password.setError("Password must contain at least 1 upper case & 1 special character!");
            return false;
        }
        else {
            sign_password.setError(null);
            sign_password.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateConfPassword() {
        String val = sign_conf_pass.getEditText().getText().toString();
        String pass = sign_password.getEditText().getText().toString();

        if(val.isEmpty()) {
            sign_conf_pass.setError("This field is required");
            return false;
        } else if(!val.matches(pass)) {
            sign_conf_pass.setError("Password didn't matched!");
            return false;
        }
        else {
            sign_conf_pass.setError(null);
            sign_conf_pass.setErrorEnabled(false);
            return true;
        }
    }
    private boolean isConnected(SignUp signUp) {
        ConnectivityManager connectivityManager = (ConnectivityManager) signUp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConnection != null && wifiConnection.isConnected()) || (mobileConnection != null && mobileConnection.isConnected())) {
            return true;
        }
        else {
            return false;
        }
    }

    public void registerUser(View view) {

        if(!validateUsername() | !validateEmail() | !validatePassword() | !validateConfPassword()) {
            return;
        }

        //Get all the input values in the String
        rootNode = FirebaseDatabase.getInstance("https://studybuddy-ai-default-rtdb.asia-southeast1.firebasedatabase.app/");
        reference = rootNode.getReference("users");

        String username = sign_username.getEditText().getText().toString();
        String email = sign_email.getEditText().getText().toString();
        String cellNo = sign_cellNo.getEditText().getText().toString();
        String password = sign_password.getEditText().getText().toString();
        UserHelper helper = new UserHelper(username, email, cellNo, password);
        reference.child(username).setValue(helper);

        Intent intent = new Intent(getApplicationContext(), Login.class);
        if(!isConnected(this)) {
            showCustomDialogue();
        }
        else {
            Toast.makeText(this, "Signup success! Login with your account!", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    private void showCustomDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
        builder.setMessage("Please connect to the internet to signup!")
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
                        startActivity(new Intent(getApplicationContext(), SignUp.class));
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
