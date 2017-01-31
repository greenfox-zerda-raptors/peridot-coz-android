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
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerMainActivityComponent;
import com.greenfox.peridot.peridot_coz_android.model.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    Button loginButton;
    TextView registerLink;
    TextView dataView;
    @Inject
    ApiService apiService;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DaggerMainActivityComponent.builder().build().inject(this);

        loginUsername = (EditText) findViewById(R.id.loginName);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerLink = (TextView) findViewById(R.id.registerHereLink);
        dataView = (TextView) findViewById(R.id.dataView);

        apiService.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();}
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }});

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfUsernameAndPasswordAreCorrectsAndLoginIfTheyAre(v);
            }
        });
    }

    public void checkIfUsernameAndPasswordAreCorrectsAndLoginIfTheyAre(View view){
        if(isUsernameOrPasswordEmpty()){
            Toast.makeText(this,"Please fill in username/password", Toast.LENGTH_SHORT).show();
        }
        else if(isUsernameAndPasswordCorrect()) {
            saveCorrectUsernameAndPasswordToSharedPreferences();
            Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
            loginWithCorrectUsernameAndPassword();
        }else{
            Toast.makeText(this,"Wrong username/password", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginWithCorrectUsernameAndPassword() {
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(loginIntent);
    }

    private void saveCorrectUsernameAndPasswordToSharedPreferences() {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("username", loginUsername.getText().toString());
        editor.putString("password", loginPassword.getText().toString());
        editor.apply();
    }

    private boolean isUsernameAndPasswordCorrect() {
        return loginUsername.getText().toString().equals(user.getUsername()) && loginPassword.getText().toString().equals(user.getPassword());
    }

    private boolean isUsernameOrPasswordEmpty() {
        return loginUsername.getText().toString().equals("") || loginPassword.getText().toString().equals("");
    }

    public void getData(View view){
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = loginData.getString("username", "");
        String pw = loginData.getString("password","");
        String msg = "Saved User Name: " + name + "\nSaved Password: " + pw;
        dataView.setText(msg);
    }
}
