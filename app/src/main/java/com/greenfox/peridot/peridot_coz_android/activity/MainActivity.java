package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.peridot.peridot_coz_android.R;

public class MainActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    TextView dataView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUsername = (EditText) findViewById(R.id.loginName);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        dataView = (TextView) findViewById(R.id.dataTextView);
    }

    //Save login info
    public void saveData(View view){
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("userName", loginUsername.getText().toString());
        editor.putString("password", loginPassword.getText().toString());

        editor.commit();

        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();

    }
    public void getData(View view){
        Toast.makeText(this,"Displaying info",Toast.LENGTH_LONG).show();
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = loginData.getString("userName", "");
        String pw = loginData.getString("password","");
        String msg = "Saved User Name: " + name + "\nSaved Password: " + pw;
        dataView.setText(msg);
    }
}
