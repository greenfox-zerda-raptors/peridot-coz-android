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
import com.greenfox.peridot.peridot_coz_android.api.ApiLoginService;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerApiComponent;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
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
    ApiLoginService apiLoginService;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DaggerApiComponent.builder().build().inject(this);
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

    public void checkIfUsernameAndPasswordAreCorrectsAndLoginIfTheyAre(View view){
        if(isUsernameOrPasswordEmpty()){
            Toast.makeText(this,"Please fill in username/password", Toast.LENGTH_SHORT).show();
        }
        else {
            apiLoginService.login(new LoginRequest(loginUsername.getText().toString(),loginPassword.getText().toString())).enqueue(new Callback<LoginAndRegisterResponse>() {
                @Override
                public void onResponse(Call<LoginAndRegisterResponse> call, Response<LoginAndRegisterResponse> response) {
                    if (response.body().getErrors() != null) {
                        if (response.body().getErrors().getUsername() != null) {
                            Toast.makeText(getApplicationContext(), response.body().getErrors().getUsername(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getErrors().getPassword(), Toast.LENGTH_SHORT).show();
                        }
                } else {
                        saveCorrectUsernameAndPasswordAndTokenToSharedPreferences(loginUsername.getText().toString(),loginPassword.getText().toString(),response.body().getToken());
                        loginWithCorrectUsernameAndPassword();
                    }
                }
                @Override
                public void onFailure(Call<LoginAndRegisterResponse> call, Throwable t) {Log.d("Error", t.getMessage());}
            });
        }
    }

    private void loginWithCorrectUsernameAndPassword() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private void saveCorrectUsernameAndPasswordAndTokenToSharedPreferences(String username, String password, String token) {
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("token", token);
        editor.apply();
    }

    private boolean isUsernameOrPasswordEmpty() {
        return loginUsername.getText().toString().equals("")
            || loginPassword.getText().toString().equals("");
    }

    public void getData(View view){
        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = loginData.getString("username", "");
        String pw = loginData.getString("password","");
        String msg = "Saved User Name: " + name + "\nSaved Password: " + pw;
        dataView.setText(msg);
    }
}
