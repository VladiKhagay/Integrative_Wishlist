package com.integrative.wishlistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.user.UserBoundary;
import com.integrative.wishlistapp.model.user.UserId;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = LoginActivity.class.getSimpleName();
    private MyApplication app;
    private DataManager dataManager;

    private EditText email_editText;
    private EditText password_editText;
    private Button login_Button;
    private TextView registerNow_Button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // remove top bar
        getSupportActionBar().hide();

        app = (MyApplication) getApplication();
        dataManager = DataManager.getInstance();
        email_editText = findViewById(R.id.email);
        password_editText = findViewById(R.id.password);
        login_Button = findViewById(R.id.loginBtn);
        registerNow_Button = findViewById(R.id.registerNowBtn);

        mAuth = FirebaseAuth.getInstance();

        setUpLoginButton();
        setUpRegisterButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        email_editText.getText().clear();
        password_editText.getText().clear();
    }

    private void setUpLoginButton(){
        this.login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailTxt = email_editText.getText().toString();
                final String passwordTxt = password_editText.getText().toString();

                if (emailTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your Email and Password", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(emailTxt, passwordTxt).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("Login", authResult.toString());

                            UserBoundary userBoundary = new UserBoundary();
                            userBoundary.setUsername(authResult.getUser().getDisplayName());
                            userBoundary.setUserId(new UserId(authResult.getUser().getEmail(), "2022b.timor.bystritskie"));
                            userBoundary.setAvatar(null);
                            dataManager.setUserBoundary(userBoundary);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Log.d("Login", e.getMessage());
                            Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }

    private void setUpRegisterButton(){
        registerNow_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // open Register activity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });
    }


}