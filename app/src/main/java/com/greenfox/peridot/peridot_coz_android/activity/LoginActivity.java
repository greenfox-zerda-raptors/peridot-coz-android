package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    Button loginButton;
    Button registerButton;
    TextView dataView;
    @Inject
    Services services;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUsername = (EditText) findViewById(R.id.loginName);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerHereButton);
        dataView = (TextView) findViewById(R.id.dataView);
        DaggerServiceComponent.builder().build().inject(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfUsernameAndPasswordAreCorrectsAndLoginIfTheyAre(v);
            }
        });
    }

    public void checkIfUsernameAndPasswordAreCorrectsAndLoginIfTheyAre(View view) {
        if (isUsernameOrPasswordEmpty()) {
            Toast.makeText(this, "Please fill in username/password", Toast.LENGTH_SHORT).show();
        } else {
            if (services.apiLoginService == null){
                Log.e("apiloginservice loginA", "apiloginservice is null");
            }else{
                Log.e("apiloginservice loginA", "apiloginservice is NOT null");
            }
            services.apiLoginService.login(new LoginRequest(loginUsername.getText().toString(), loginPassword.getText().toString())).enqueue(new Callback<LoginAndRegisterResponse>() {
                @Override
                public void onResponse(Call<LoginAndRegisterResponse> call, Response<LoginAndRegisterResponse> response) {
                    if (response.body().getErrors() != null) {
                        if (response.body().getErrors().getUsername() != null) {
                            Toast.makeText(getApplicationContext(), response.body().getErrors().getUsername(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getErrors().getPassword(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        saveCorrectUsernameAndPasswordAndTokenToSharedPreferences(loginUsername.getText().toString(), response.body().getToken());
                        services.setApiService();
                        Toast.makeText(getApplicationContext(), "Welcome " + loginUsername.getText().toString() + "!", Toast.LENGTH_SHORT).show();

                        loginWithCorrectUsernameAndPassword();
                    }
                }

                @Override
                public void onFailure(Call<LoginAndRegisterResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
    }

    private void loginWithCorrectUsernameAndPassword() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void saveCorrectUsernameAndPasswordAndTokenToSharedPreferences(String username, String token) {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("username", username);
        editor.putString("token", token);
        editor.apply();
    }

    private boolean isUsernameOrPasswordEmpty() {
        return loginUsername.getText().toString().equals("")
                || loginPassword.getText().toString().equals("");
    }

    public void getData() {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = loginData.getString("username", "");
        String token = loginData.getString("token", "");
        String msg = "Saved User Name: " + name + "\nSaved Token: " + token;
        dataView.setText(msg);
    }
}
