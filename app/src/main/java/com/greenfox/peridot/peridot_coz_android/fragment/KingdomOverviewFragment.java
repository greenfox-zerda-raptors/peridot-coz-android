package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.activity.MainActivity;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Kingdom;
import com.greenfox.peridot.peridot_coz_android.model.response.KingdomResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerApiComponent;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KingdomOverviewFragment extends Fragment {

    @Inject
    ApiService apiService;
    Kingdom kingdom;
    Button buildingButton, troopButton, resourceButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.kingdom_overview_layout, container, false);
        DaggerApiComponent.builder().build().inject(this);
        Log.e("kingdomfragment", "default fragmnet start");
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

        apiService.getKingdom().enqueue(new Callback<KingdomResponse>() {
            @Override
            public void onResponse(Call<KingdomResponse> call, Response<KingdomResponse> response) {
                if (response.body().getErrors() == null) {
                    kingdom = response.body().getKingdom();
                    Toast.makeText(getContext(), "Welcome in kingdom of " + kingdom.getUser().getKingdom() + "!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Something went wrong, please try to refresh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KingdomResponse> call, Throwable t) {
            }
        });

        return contentView;
    }
}
