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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.integrative.wishlistapp.manager.DataManager;
import com.integrative.wishlistapp.model.Product;
import com.integrative.wishlistapp.model.Wishlist;
import com.integrative.wishlistapp.model.instance.CreatedBy;
import com.integrative.wishlistapp.model.instance.InstanceBoundary;
import com.integrative.wishlistapp.model.instance.InstanceId;
import com.integrative.wishlistapp.model.instance.Location;
import com.integrative.wishlistapp.model.user.UserId;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    MyApplication app;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    private EditText fullName_editText;
    private EditText email_editText;
    private EditText phone_editText;
    private EditText password_editText;
    private EditText conPassword_editText;
    private Button register_Button;
    private TextView loginNow_TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        app = (MyApplication)getApplication();
        fullName_editText = findViewById(R.id.fullName);
        email_editText = findViewById(R.id.email);
        phone_editText = findViewById(R.id.phone);
        password_editText = findViewById(R.id.password);
        conPassword_editText = findViewById(R.id.conPassword);

        register_Button = findViewById(R.id.registerBtn);
        loginNow_TextView = findViewById(R.id.loginNow);

        setUpRegisterButton();
        setUpLoginNowButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullName_editText.getText().clear();
        email_editText.getText().clear();
        phone_editText.getText().clear();
        password_editText.getText().clear();
        conPassword_editText.getText().clear();
    }

    private void setUpRegisterButton() {
        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get data from EditTexts into String variables
                final String fullNameTxt = fullName_editText.getText().toString();
                final String emailTxt = email_editText.getText().toString();
                final String phoneTxt = phone_editText.getText().toString();
                final String passwordTxt = password_editText.getText().toString();
                final String conPasswordTxt = conPassword_editText.getText().toString();
                // check if user fill all the fields before sending data to firebase
                if (fullNameTxt.isEmpty() || emailTxt.isEmpty() || phoneTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

                // check if password are matching with each other
                // if not matching with each other then show a toast message
                else if (!passwordTxt.equals(conPasswordTxt)) {
                    Toast.makeText(RegisterActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "start registration");

                    firebaseAuth.createUserWithEmailAndPassword(emailTxt, passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser rUser = firebaseAuth.getCurrentUser();
                                String userId = rUser.getUid();
                                createWishlist(emailTxt);
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Failed register new user" + task.getResult(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    private void setUpLoginNowButton(){
        loginNow_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void createWishlist(String userEmail) {
        InstanceBoundary instanceBoundary = new InstanceBoundary();
        instanceBoundary.setInstanceId(new InstanceId(AppConstants.DOMAIN, null));
        instanceBoundary.setType(AppConstants.WISHLIST);
        instanceBoundary.setName("wishlist name");
        instanceBoundary.setActive(true);
        instanceBoundary.setCreatedTimestamp(new Date());
        instanceBoundary.setCreatedBy(new CreatedBy(new UserId(userEmail, AppConstants.DOMAIN)));
        instanceBoundary.setLocation(new Location(100.0, 100.0));
        instanceBoundary.setInstanceAttributes(new HashMap<String,Object>());
        Wishlist wishlist = new Wishlist();
        wishlist.setDescription("My Wishlist");
        wishlist.setProducts(new ArrayList<Product>());


        Type mapType;
        mapType = new TypeToken<Map<String,Object>>(){}.getType();
        Gson gson = gson = new GsonBuilder().create();
        String wishlistJson =gson.toJson(wishlist);
        Map<String , Object> wishlistAsMap = gson.fromJson(wishlistJson, mapType);
        instanceBoundary.setInstanceAttributes(wishlistAsMap);
        app.getInstancesRepository().createInstance(instanceBoundary);
    }
}