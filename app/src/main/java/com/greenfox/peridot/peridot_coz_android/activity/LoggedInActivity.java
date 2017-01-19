package com.greenfox.peridot.peridot_coz_android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;

public class LoggedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_activity);

        final TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
    }
}
