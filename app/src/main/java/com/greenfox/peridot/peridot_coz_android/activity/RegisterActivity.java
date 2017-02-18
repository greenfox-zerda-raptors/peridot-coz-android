package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText regUsername;
    private EditText regPassword;
    private EditText regKingdomName;

    @Inject
    Services services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        DaggerServiceComponent.builder().build().inject(this);
        regUsername = (EditText) findViewById(R.id.regUsername);
        regPassword = (EditText) findViewById(R.id.regPassword);
        regKingdomName = (EditText) findViewById(R.id.regKingdom);
        final Button regButton = (Button) findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfFieldsAreNotEmptyThenRegisterIn(v);
            }
        });

    }

    private void checkIfFieldsAreNotEmptyThenRegisterIn(View v) {
        if (isUsernameOrPasswordOrKingdomEmpty()) {
            Toast.makeText(this, "Please fill out every field!", Toast.LENGTH_SHORT).show();
        } else {
            services.apiLoginService.register(new RegisterRequest(regUsername.getText().toString(), regPassword.getText().toString(), regKingdomName.getText().toString())).enqueue(new Callback<LoginAndRegisterResponse>() {
                @Override
                public void onResponse(Call<LoginAndRegisterResponse> call, Response<LoginAndRegisterResponse> response) {
                    saveCorrectUsernameAndPasswordToSharedPreferences(regUsername.getText().toString(), response.body().getToken());
                    loginWithCorrectPassword();
                    Toast.makeText(getApplicationContext(), "Welcome " + regUsername.getText().toString() + "!", Toast.LENGTH_SHORT).show();

                }
                @Override
                public void onFailure(Call<LoginAndRegisterResponse> call, Throwable t) {
                }
            });
        }
    }

    private void loginWithCorrectPassword() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void saveCorrectUsernameAndPasswordToSharedPreferences(String username, String token) {
        SharedPreferences registerData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = registerData.edit();
        editor.putString("username", username);
        editor.putString("token", token);
        editor.apply();
    }

    private boolean isUsernameOrPasswordOrKingdomEmpty() {
        return regUsername.getText().toString().equals("")
                || regPassword.getText().toString().equals("") || regKingdomName.getText().toString().equals("");
    }
}
