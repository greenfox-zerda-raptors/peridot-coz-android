package com.greenfox.peridot.peridotcozandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText regUsername = (EditText) findViewById(R.id.regUsername);
        final EditText regPassword = (EditText) findViewById(R.id.regPassword);
        final Button regButton = (Button) findViewById(R.id.regButton);
    }
}