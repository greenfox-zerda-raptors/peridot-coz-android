package com.greenfox.peridot.peridotcozandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginUsername = (EditText) findViewById(R.id.loginName);
        final EditText loginPassword = (EditText) findViewById(R.id.loginPassword);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        final TextView registerLink = (TextView) findViewById(R.id.registerHereLink);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });


    }
}
