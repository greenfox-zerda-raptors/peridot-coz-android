package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

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
        DaggerServiceComponent.builder().build().inject(this);
        loginUsername = (EditText) findViewById(R.id.loginName);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerHereButton);
        dataView = (TextView) findViewById(R.id.dataView);

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

    @Override
    public void onData(Call call, Response response) {
        LoginResponse loginResponse = (LoginResponse) response.body();
        if (loginResponse.getErrors() != null){
            if (loginResponse.getErrors().getUsername() != null ){
                Toast.makeText(getApplicationContext(), loginResponse.getErrors().getUsername(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), loginResponse.getErrors().getPassword(), Toast.LENGTH_SHORT).show();
            }
        } else {
            saveCorrectUsernameAndPasswordAndTokenToSharedPreferences(loginUsername.getText().toString(), loginPassword.getText().toString(), loginResponse.getToken());
            loginWithCorrectUsernameAndPassword();
        }
    }

    @Override
    public void onError(Call call, Throwable t) {
        Log.d("Error", t.getMessage());
    }

    public void checkIfUsernameAndPasswordAreCorrectsAndLoginIfTheyAre(View view) {
        deleteSharedPreferences();
        if (isUsernameOrPasswordEmpty()) {
            Toast.makeText(this, "Please fill in username/password", Toast.LENGTH_SHORT).show();
        } else {

            services.apiLoginService.login(new LoginRequest(loginUsername.getText().toString(), loginPassword.getText().toString())).enqueue(this); {
            }
        }
    }

    private void loginWithCorrectUsernameAndPassword() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void saveCorrectUsernameAndPasswordAndTokenToSharedPreferences(String username, String password, String token) {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("token", token);
        editor.apply();
        services.setApiService();
    }

    private boolean isUsernameOrPasswordEmpty() {
        return loginUsername.getText().toString().equals("")
                || loginPassword.getText().toString().equals("");
    }

    public void getData(View view) {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = loginData.getString("username", "");
        String pw = loginData.getString("password", "");
        String msg = "Saved User Name: " + name ;
        dataView.setText(msg);
    }

    private void deleteSharedPreferences() {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.putString("token", "");
        editor.apply();
        services.setApiService();
    }
}
