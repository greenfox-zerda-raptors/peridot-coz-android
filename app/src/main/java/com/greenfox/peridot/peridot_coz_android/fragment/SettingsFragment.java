package com.greenfox.peridot.peridot_coz_android.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.R;

import retrofit2.Call;
import retrofit2.Response;

public class SettingsFragment extends android.support.v4.app.Fragment {

    Switch enableNotifications;
    Switch enableBackgroundSync;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.toolbar_settings_layout, container, false);

        enableNotifications = (Switch) contentView.findViewById(R.id.enableNotifications);
        enableNotifications.setChecked(true);
        enableNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    modifySharedPreferences("notificationsEnabled", isChecked);
                    Toast.makeText(getActivity(),"Notifications Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    modifySharedPreferences("notificationsEnabled", isChecked);
                    Toast.makeText(getActivity(),"Notifications Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        enableBackgroundSync = (Switch) contentView.findViewById(R.id.enableBackgroundSync);
        enableBackgroundSync.setChecked(true);
        enableBackgroundSync.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    modifySharedPreferences("backgroundSyncEnabled", isChecked);
                    Toast.makeText(getActivity(),"Background Sync Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    modifySharedPreferences("backgroundSyncEnabled", isChecked);
                    Toast.makeText(getActivity(),"Background Sync Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return contentView;
    }

    private void modifySharedPreferences(String modifyThis, boolean isEnabled ) {
        SharedPreferences preferences = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(modifyThis, isEnabled);
        editor.apply();
    }
}
