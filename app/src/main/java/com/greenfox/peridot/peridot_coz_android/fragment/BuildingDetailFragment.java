package com.greenfox.peridot.peridot_coz_android.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingDetailFragment extends Fragment {

    @Inject
    Services services;
    private Building building;
    ProgressDialog progressDialog = ProgressDialog.show(getActivity().getApplicationContext(),"", "...loading", false);

    public BuildingDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.building_detail, container, false);
        DaggerServiceComponent.builder().build().inject(this);

        Bundle bundle = getArguments();
        Building buildingFromPrevFrag = (Building) bundle.getSerializable("building");


        services.apiService.getDetailsOfBuilding(buildingFromPrevFrag.getId()).enqueue(new Callback<Building>() {
            @Override
            public void onResponse(Call<Building> call, Response<Building> response) {
                building = response.body();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Building> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

        ImageView buildingImage = (ImageView) contentView.findViewById(R.id.detailBuildingImage);
        TextView buildingType = (TextView) contentView.findViewById(R.id.buildingName);
        TextView buildingLevel = (TextView) contentView.findViewById(R.id.buildingLevel);
        if (building.getType().equals("Townhall")) {
            buildingImage.setImageResource(R.drawable.town);
        }
        if (building.getType().equals("Farm")) {
            buildingImage.setImageResource(R.drawable.farm);
        }
        if (building.getType().equals("Mine")) {
            buildingImage.setImageResource(R.drawable.mine);
        }
        if (building.getType().equals("Barrack")) {
            buildingImage.setImageResource(R.drawable.barrack);
        }
        buildingType.setText(String.valueOf(building.getType()));
        buildingLevel.setText(String.valueOf("Level " + building.getLevel()));

        return contentView;
    }
}
