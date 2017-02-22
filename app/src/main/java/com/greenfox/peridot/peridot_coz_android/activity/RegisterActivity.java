<<<<<<< HEAD
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
import com.greenfox.peridot.peridot_coz_android.api.ApiLoginService;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerApiComponent;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginAndRegisterResponse;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    private EditText regUsername;
    private EditText regPassword;
    private EditText regKingdomName;
    @Inject
    ApiLoginService apiLoginService;
    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        DaggerApiComponent.builder().build().inject(this);
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

    @Override
    public void onData(Call call, Response response) {
        saveCorrectUsernameAndPasswordToSharedPreferences();
        loginWithCorrectPassword();
        Toast.makeText(getApplicationContext(), "Thank you " + regUsername.getText().toString() + ", but we have to use the username in the mockserver, SORRY!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Call call, Throwable t) {

    }

    private void checkIfFieldsAreNotEmptyThenRegisterIn(View v) {
        if (isUsernameOrPasswordOrKingdomEmpty()) {
            Toast.makeText(this, "Please fill out every field!", Toast.LENGTH_SHORT).show();
        } else {
            apiLoginService.register(new RegisterRequest(regUsername.getText().toString(), regPassword.getText().toString(), regKingdomName.getText().toString())).enqueue(this);{
            }
        }
    }

    private void loginWithCorrectPassword() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }

    private void saveCorrectUsernameAndPasswordToSharedPreferences() {
        SharedPreferences registerData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = registerData.edit();
        editor.putString("username", "aaa");
        editor.putString("password", "aaa");
        editor.apply();
    }

    private boolean isUsernameOrPasswordOrKingdomEmpty() {
        return regUsername.getText().toString().equals("")
                || regPassword.getText().toString().equals("") || regKingdomName.getText().toString().equals("");
    }
}
=======
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
import android.widget.Toast;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.request.RegisterRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.Response;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    private EditText regUsername;
    private EditText regPassword;
    private EditText regKingdomName;
    private EditText regFirstName;
    private EditText regLastName;
    private EditText regEmail;

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
        regFirstName = (EditText) findViewById(R.id.regFirstName);
        regLastName = (EditText) findViewById(R.id.regLastName);
        regEmail = (EditText) findViewById(R.id.regEmail);
        final Button regButton = (Button) findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfFieldsAreNotEmptyThenRegisterIn(v);
            }
        });

    }

    private void checkIfFieldsAreNotEmptyThenRegisterIn(View v) {
        if (isAnyRequiredFieldEmpty()) {
            Toast.makeText(this, "Please fill out every field!", Toast.LENGTH_SHORT).show();
        } else {
            services.apiLoginService.register(new RegisterRequest(regUsername.getText().toString(), regPassword.getText().toString(), regKingdomName.getText().toString(), regFirstName.getText().toString(), regLastName.getText().toString(), regEmail.getText().toString())).enqueue(new Callback<Response>() {

                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    saveCorrectUsernameAndPasswordToSharedPreferences(regUsername.getText().toString(), regPassword.getText().toString());
                    goToLoginWithNewUser();
                    Toast.makeText(getApplicationContext(), "Welcome " + regUsername.getText().toString() + "!", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                }
            });
        }
    }

    private void goToLoginWithNewUser() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void saveCorrectUsernameAndPasswordToSharedPreferences(String username, String password) {
        SharedPreferences registerData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = registerData.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private boolean isAnyRequiredFieldEmpty() {
        return regUsername.getText().toString().equals("")
                || regPassword.getText().toString().equals("")
                || regKingdomName.getText().toString().equals("")
                || regFirstName.getText().toString().equals("")
                || regLastName.getText().toString().equals("")
                || regEmail.getText().toString().equals("");
    }
}
>>>>>>> master
