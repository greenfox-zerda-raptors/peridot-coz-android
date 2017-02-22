package com.greenfox.peridot.peridot_coz_android.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.fragment.BattleOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.BuildingsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.KingdomOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.ResourcesOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.SettingsFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.TroopsOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.fragment.UserOverviewFragment;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.request.LoginRequest;
import com.greenfox.peridot.peridot_coz_android.model.response.LoginResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    Kingdom kingdom;
    @Inject
    Services services;

    @Override
    public void onData(Call call, Response response) {
        LoginResponse loginResponse = (LoginResponse) response.body();
        Log.e("response", loginResponse.getToken());
        if (loginResponse.getErrors() == null){
            String token = loginResponse.getToken();
            services.setApiService();
            Toast.makeText(getApplicationContext(), "Welcome " + token + "!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Something went wrong, please log in again", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    @Override
    public void onError(Call call, Throwable t) {
        Log.d("Error", t.getMessage());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerServiceComponent.builder().build().inject(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        if(SharedPreferencesTokenEmpty() && checkSharedPreferencesForUser()) {
            services.apiLoginService.login(new LoginRequest(getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username", ""), getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("password", ""))).enqueue(this); {

            }
        }

        if (!SharedPreferencesTokenEmpty()){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            if (getIntent().getStringExtra("notification") != null) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(Integer.valueOf(getIntent().getStringExtra("notificationID")));
            }
            if (getIntent().getStringExtra("fragment")== null) {
                loadFragment(new KingdomOverviewFragment());
            } else if (getIntent().getStringExtra("fragment").equals("buildings")) {
                loadFragment(new BuildingsOverviewFragment());
            }}
    }

    @Override
    protected void onPause() {
        saveBuildingCountToSharedPreferences();
        saveTroopCountToSharedPreferences();
    }

    private void saveBuildingCountToSharedPreferences(){
        SharedPreferences buildingCount = getSharedPreferences("buildings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = buildingCount.edit();
        int buildings = kingdom.getBuildings().size();
        editor.putInt("buildings", buildings);
        editor.apply();
    }
    private void saveTroopCountToSharedPreferences(){
        SharedPreferences troopCount = getSharedPreferences("troops", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = troopCount.edit();
        int troops = kingdom.getTroops().size();
        editor.putInt("troops", troops);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            loadFragment(new SettingsFragment());
            return true;
        } else if (id == R.id.statistics) {
            Toast.makeText(this, "Statistics go here.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.logout) {
            logout();
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
            loadFragment(new KingdomOverviewFragment());
        } else if (id == R.id.nav_buildings) {
            loadFragment(new BuildingsOverviewFragment());
        } else if (id == R.id.nav_troops) {
            loadFragment(new TroopsOverviewFragment());
        } else if (id == R.id.nav_battle) {
            loadFragment(new BattleOverviewFragment());
        } else if (id == R.id.nav_resources) {
            loadFragment(new ResourcesOverviewFragment());
        } else if (id == R.id.nav_user) {
            loadFragment(new UserOverviewFragment());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean checkSharedPreferencesForUser() {
        if (getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("username", "").equals("")) {
            Toast.makeText(this, "You have to log in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            return false;
        }else{
            return true;
        }
    }

    private boolean SharedPreferencesTokenEmpty() {
        return getSharedPreferences("userInfo", Context.MODE_PRIVATE).getString("token", "").equals("");
    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.putString("token", "");
        editor.apply();
        Toast.makeText(this, "Successful logout", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
    }
}
