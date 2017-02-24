package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.greenfox.peridot.peridot_coz_android.R.id.troopImage;

public class TroopDetailFragment extends Fragment {

    @Inject
    Services services;
    private Troop troop;

    public TroopDetailFragment() {}

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
            }

            @Override
            public void onFailure(Call<Building> call, Throwable t) {

            }
        });

        ImageView buildingImage = (ImageView) contentView.findViewById(R.id.detailBuildingImage);
        TextView buildingType = (TextView) contentView.findViewById(R.id.buildingName);
        TextView buildingLevel = (TextView) contentView.findViewById(R.id.buildingLevel);
        troopImage.setImageResource(R.drawable.troop);
        buildingType.setText(String.valueOf(building.getType()));
        buildingLevel.setText(String.valueOf("Level " + building.getLevel()));

        return contentView;
    }
}
