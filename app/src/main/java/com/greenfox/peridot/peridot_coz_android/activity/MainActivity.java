package com.greenfox.peridot.peridot_coz_android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerMainActivityComponent;
import com.greenfox.peridot.peridot_coz_android.fragment.BattleOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.KingdomOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.SettingsFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.TroopsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.model.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.User;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    User user;
    @Inject
    ApiService apiService;
    TextView welcomeText;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        DaggerMainActivityComponent.builder().build().inject(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        KingdomOverviewFragment kingdomOverviewFragment= new KingdomOverviewFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, kingdomOverviewFragment);
        fragmentTransaction.commit();
    }

    public void checkSharedPreferencesForUser(User user) {
        if(getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username","").equals("")){
            Toast.makeText(this,"You have to log in", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }

    public void logout(){
        SharedPreferences preferences = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.apply();
        Toast.makeText(this,"Successful logout", Toast.LENGTH_SHORT).show();

        Intent loginIntent = new Intent(this, LoginActivity.class);
        this.startActivity(loginIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        getMenuInflater().inflate(R.menu.left_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.icon) {
            Toast.makeText(this, "Megnyomtad a csillagikont", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.settings) {
            SettingsFragment settingsFragment= new SettingsFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, settingsFragment);
            fragmentTransaction.commit();
            return true;
        } else if (id == R.id.statistics) {
            Toast.makeText(this, "Itt jonnek majd a statisztikak", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;

        } else if (id == R.id.action_settings) {
            Toast.makeText(this, "Megnyomtad az Action settingset", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_kingdom_overview) {
            KingdomOverviewFragment kingdomOverviewFragment= new KingdomOverviewFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, kingdomOverviewFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_buildings) {
            BuildingsOverviewFragment buildingsOverviewFragment = new BuildingsOverviewFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, buildingsOverviewFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_troops) {
            TroopsOverviewFragment troopsOverviewFragment = new TroopsOverviewFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, troopsOverviewFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_battle) {
            BattleOverviewFragment battleOverviewFragment = new BattleOverviewFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, battleOverviewFragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
