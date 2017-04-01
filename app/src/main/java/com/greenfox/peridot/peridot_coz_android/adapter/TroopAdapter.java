package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TroopAdapter extends ArrayAdapter<Troop>{

    @Inject
    Services services;
    Troop upgradedTroop;
    public TroopAdapter(Context context, ArrayList<Troop> troops) {
        super(context, 0, troops);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Troop troop = super.getItem(position);
        DaggerServiceComponent.builder().build().inject(this);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.troops_overview_listitem, parent, false);
        }
        // Lookup view for data population
        final ImageView upgradeImage = (ImageView) convertView.findViewById(R.id.upgrade);
        TextView tvHP = (TextView) convertView.findViewById(R.id.tvHP);
        TextView tvAP = (TextView) convertView.findViewById(R.id.tvAP);
        TextView tvDP = (TextView) convertView.findViewById(R.id.tvDP);
        // Populate the data into the template view using the data object
        tvHP.setText(String.valueOf("HP: " + troop.getHp()));
        tvAP.setText(String.valueOf("AP: " + troop.getAttackPower()));
        tvDP.setText(String.valueOf("DP: " + troop.getDefensePower()));

        upgradeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.apiService.upgradeTroop(troop).enqueue(new Callback<Troop>() {
                    @Override
                    public void onResponse(Call<Troop> call, Response<Troop> response) {
                        upgradedTroop = response.body();
                        Bundle bundles = new Bundle();
                        bundles.putSerializable("upgradetroop", upgradedTroop);
                    }

                    @Override
                    public void onFailure(Call<Troop> call, Throwable t) {

                    }
                });
            }
        });

        return convertView;
    }
}
