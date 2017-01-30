package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    Button logoutButton;
    User user;
    @Inject
    ApiService apiService;
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        DaggerMainActivityComponent.builder().build().inject(this);
        apiService.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                checkSharedPreferencesForUser(user);
                welcomeText.setText("Welcome " + user.getUsername() + "!");
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                }});
        logoutButton = (Button) findViewById(R.id.logoutButton);
    }

    public void checkSharedPreferencesForUser(User user) {
        if(getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username","").equals("")){
            Toast.makeText(this,"You have to log in", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    public void logout(View v){
        SharedPreferences preferences =getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.apply();
        Toast.makeText(this,"Successful logout", Toast.LENGTH_SHORT).show();
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }
}
