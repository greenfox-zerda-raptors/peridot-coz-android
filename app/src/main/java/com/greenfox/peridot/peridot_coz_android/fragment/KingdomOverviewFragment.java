package com.greenfox.peridot.peridot_coz_android.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.LoginActivity;


/**
 * Created by mozgaanna on 25/01/17.
 */

public class KingdomOverviewFragment extends Fragment {
    Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.kingdom_overview_layout, container, false);
        logoutButton = (Button) contentView.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        return contentView;
    }


    public void logout(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.apply();
        Toast.makeText(getActivity(),"Successful logout", Toast.LENGTH_SHORT).show();

        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(loginIntent);
    }
}
