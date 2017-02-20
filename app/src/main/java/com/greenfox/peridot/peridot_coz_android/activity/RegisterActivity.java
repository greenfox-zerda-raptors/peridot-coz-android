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
