package com.greenfox.peridot.peridot_coz_android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingAdapter extends ArrayAdapter<Building> {

    @Inject
    Services services;
    Building upgradedBuilding;

    public BuildingAdapter(Context context, ArrayList<Building> buildings) {
        super(context, 0, buildings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Building building = super.getItem(position);
        DaggerServiceComponent.builder().build().inject(this);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.buildings_overview_listitem, parent, false);
        }
        ImageView buildingImage = (ImageView) convertView.findViewById(R.id.buildingImage);
        final ImageView upgradeImage = (ImageView) convertView.findViewById(R.id.upgrade);
        TextView buildingType = (TextView) convertView.findViewById(R.id.buildingName);
        TextView buildingLevel = (TextView) convertView.findViewById(R.id.buildingLevel);
        if (building.getType().equals("Townhall")) {buildingImage.setImageResource(R.drawable.town);}
        if (building.getType().equals("Farm")) {buildingImage.setImageResource(R.drawable.farm);}
        if (building.getType().equals("Mine")) {buildingImage.setImageResource(R.drawable.mine);}
        if (building.getType().equals("Barrack")) {buildingImage.setImageResource(R.drawable.barrack);}
        buildingType.setText(String.valueOf(building.getType() + " " + (position+1)));
        buildingLevel.setText(String.valueOf("Level " + building.getLevel()));

        upgradeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.apiService.upgradeBuilding(building).enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        upgradedBuilding = response.body();
                        Bundle bundles = new Bundle();
                        bundles.putSerializable("upgradebuilding", upgradedBuilding);
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
            }
        });

        return convertView;
    }


}
