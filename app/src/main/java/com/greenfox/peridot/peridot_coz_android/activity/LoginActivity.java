package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.peridot.peridot_coz_android.R;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    Button loginButton;
    TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = (EditText) findViewById(R.id.loginName);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());



        registerLink = (TextView) findViewById(R.id.registerHereLink);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }


        //Save login info
    public void save(View view){
//        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
////        SharedPreferences.Editor editor = loginData.edit();
////        editor.putString("userName", loginUsername.getText().toString());
////        editor.putString("password", loginPassword.getText().toString());
////        editor.apply();

        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();

//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent loginIntent = new Intent(LoginActivity.this, LoggedInActivity.class);
//                LoginActivity.this.startActivity(loginIntent);
//            }
//        });

    }
}
