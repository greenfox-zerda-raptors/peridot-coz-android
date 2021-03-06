package com.greenfox.peridot.peridot_coz_android.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenfox.peridot.peridot_coz_android.CozApp;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.BuildingAdapter;
import com.greenfox.peridot.peridot_coz_android.backgroundSync.BuildingsEvent;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.VIBRATOR_SERVICE;

public class BuildingsOverviewFragment extends BaseFragment {

    private BuildingAdapter adapter;
    private Building upgradedBuilding;
    private int counter = 164;
    @Inject
    Services services;
    FloatingActionButton mainFab, mineFab, farmFab, barrackFab, townhallFab;
    boolean isMainFabOpen;
    Animation mainFabRotateLeft, mainFabRotateRight, appearSmallFab, disappearSmallFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.buildings_overview_layout, container, false);
        DaggerServiceComponent.builder().build().inject(this);

        mainFab = (FloatingActionButton) contentView.findViewById(R.id.mainFab);
        mineFab = (FloatingActionButton) contentView.findViewById(R.id.mineFab);
        farmFab = (FloatingActionButton) contentView.findViewById(R.id.farmFab);
        barrackFab = (FloatingActionButton) contentView.findViewById(R.id.barrackFab);
        townhallFab = (FloatingActionButton) contentView.findViewById(R.id.townhallFab);
        isMainFabOpen = false;
        mainFabRotateRight = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_main_fab_left);
        mainFabRotateLeft = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_main_fab_right);
        appearSmallFab = AnimationUtils.loadAnimation(getContext(), R.anim.appear_small_fab);
        disappearSmallFab = AnimationUtils.loadAnimation(getContext(), R.anim.disappear_small_fab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAndCloseFabs();
            }
        });

        mineFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building mine = new Building(counter, "Mine");
                counter++;
                createNewBuilding(mine);
            }
        });
        farmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building farm = new Building(counter, "Farm");
                counter++;
                createNewBuilding(farm);
            }
        });
        barrackFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building barrack = new Building(counter, "Barrack");
                counter++;
                createNewBuilding(barrack);
            }
        });
        townhallFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building townhall = new Building(counter, "Townhall");
                counter++;
                createNewBuilding(townhall);
            }
        });
        final ListView listView = (ListView) contentView.findViewById(R.id.listViewBuilding);
        adapter = new BuildingAdapter(container.getContext(), new ArrayList<Building>());
        listView.setAdapter(adapter);
        services.apiService.getBuildings().enqueue(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundles = new Bundle();
                Building building = (Building) listView.getAdapter().getItem(position);
                bundles.putSerializable("building", building);
                BuildingDetailFragment frag = new BuildingDetailFragment();
                frag.setArguments(bundles);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, frag)
                        .addToBackStack(null)
                        .commit();
            }
        });

        saveBuildingCountToSharedPreferences();
        return contentView;
    }

    @Override
    public void onData(Call call, Response response) {
        BuildingsResponse building = (BuildingsResponse) response.body();
        adapter.clear();
        adapter.addAll(building.getBuildings());
    }

    @Override
    public void onError(Call call, Throwable t) {
    }

    private void createNewBuilding(final Building building) {
        services.apiService.createBuilding(building).enqueue(new Callback<Building>() {
            @Override
            public void onResponse(Call<Building> call, Response<Building> response) {
                adapter.add(response.body());
            }

            @Override
            public void onFailure(Call<Building> call, Throwable t) {

            }
        });

    }

    private void upgradeChosenBuilding(final Building building) {
        Bundle bundle = getArguments();
        upgradedBuilding = (Building) bundle.getSerializable("upgradebuilding");
        adapter.remove(building);
        adapter.add(upgradedBuilding);
    }


    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        saveBuildingCountToSharedPreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        saveBuildingCountToSharedPreferences();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
        saveBuildingCountToSharedPreferences();
    }

    private void openAndCloseFabs() {
        if (isMainFabOpen) {
            mainFab.startAnimation(mainFabRotateLeft);
            mineFab.startAnimation(disappearSmallFab);
            farmFab.startAnimation(disappearSmallFab);
            barrackFab.startAnimation(disappearSmallFab);
            townhallFab.startAnimation(disappearSmallFab);
            isMainFabOpen = false;
        } else {
            mainFab.startAnimation(mainFabRotateRight);
            mineFab.startAnimation(appearSmallFab);
            farmFab.startAnimation(appearSmallFab);
            barrackFab.startAnimation(appearSmallFab);
            townhallFab.startAnimation(appearSmallFab);
            isMainFabOpen = true;
        }
        mineFab.setClickable(isMainFabOpen);
        farmFab.setClickable(isMainFabOpen);
        barrackFab.setClickable(isMainFabOpen);
        townhallFab.setClickable(isMainFabOpen);
    }

    @Subscribe
    public void onBuildingsEvent(BuildingsEvent buildingsEvent) {
        services.apiService.getBuildings().enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                adapter.clear();
                adapter.addAll(response.body().getBuildings());
            }

            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {
            }
        });
        Vibrator vibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(500);

    }

    private void saveBuildingCountToSharedPreferences() {
        SharedPreferences buildingCount = CozApp.getApplication().getSharedPreferences("buildings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = buildingCount.edit();
        editor.putInt("buildings", adapter.getCount());
        editor.apply();
    }
}
