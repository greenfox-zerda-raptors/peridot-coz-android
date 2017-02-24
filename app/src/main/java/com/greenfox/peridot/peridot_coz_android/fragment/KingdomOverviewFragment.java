package com.greenfox.peridot.peridot_coz_android.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.CozApp;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Response;

public class KingdomOverviewFragment extends BaseFragment<KingdomResponse> {

    @Inject
    Services services;
    Kingdom kingdom;
    Button buildingButton, troopButton, resourceButton;
    TextView tvBuildings;
    TextView tvResourcesGold;
    TextView tvResourcesFood;
    TextView tvTroops;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.kingdom_overview_layout, container, false);
        DaggerServiceComponent.builder().build().inject(this);
        buildingButton = (Button) contentView.findViewById(R.id.buildings_button);
        troopButton = (Button) contentView.findViewById(R.id.troops_button);
        resourceButton = (Button) contentView.findViewById(R.id.resources_button);

        buildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(new BuildingsOverviewFragment());
            }
        });
        troopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(new TroopsOverviewFragment());
            }
        });
        resourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).loadFragment(new ResourcesOverviewFragment());
            }
        });

        tvBuildings = (TextView) contentView.findViewById(R.id.textview_finished_buildings);
        tvResourcesGold = (TextView) contentView.findViewById(R.id.textview_gold);
        tvResourcesFood = (TextView) contentView.findViewById(R.id.textview_food);
        tvTroops = (TextView) contentView.findViewById(R.id.textview_finished_troops);

        services.apiService.getKingdom().enqueue(this);
        saveBuildingCountToSharedPreferences();
        saveTroopCountToSharedPreferences();

        return contentView;

    }

    @Override
    public void onResume() {
        super.onResume();
        saveBuildingCountToSharedPreferences();
        saveTroopCountToSharedPreferences();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveBuildingCountToSharedPreferences();
        saveTroopCountToSharedPreferences();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        saveBuildingCountToSharedPreferences();
        saveTroopCountToSharedPreferences();
    }

    @Override
    public void onData(Call call, Response response) {
        KingdomResponse kingdomResponse = (KingdomResponse) response.body();
        if (kingdomResponse.getErrors() == null) {
            kingdom = kingdomResponse.getKingdom();
            tvBuildings.setText(String.valueOf(kingdom.buildingsCount() + " finished"));
            tvResourcesGold.setText(String.valueOf(kingdom.goldCount() + " gold"));
            tvResourcesFood.setText(String.valueOf(kingdom.foodCount() + " food"));
            tvTroops.setText(String.valueOf(kingdom.troopsCount() + " finished"));
        } else {
            Toast.makeText(getContext(), "Something went wrong, please try to refresh", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Call call, Throwable t) {

    }



    private void saveBuildingCountToSharedPreferences() {
        SharedPreferences buildingCount = CozApp.getApplication().getSharedPreferences("buildings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = buildingCount.edit();
        int buildings = kingdom.getBuildings().size();
        editor.putInt("buildings", buildings);
        editor.apply();
    }

    private void saveTroopCountToSharedPreferences() {
        SharedPreferences troopCount = CozApp.getApplication().getSharedPreferences("troops", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = troopCount.edit();
        int troops = kingdom.getTroops().size();
        editor.putInt("troops", troops);
        editor.apply();
    }
}
