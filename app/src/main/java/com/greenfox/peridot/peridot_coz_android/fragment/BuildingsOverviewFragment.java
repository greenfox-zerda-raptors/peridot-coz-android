package com.greenfox.peridot.peridot_coz_android.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.Toast;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.BuildingAdapter;
import com.greenfox.peridot.peridot_coz_android.backgroundSync.SyncService;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerMainActivityComponent;
import com.greenfox.peridot.peridot_coz_android.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuildingsOverviewFragment extends Fragment {

    private ArrayList<Building> buildings = new ArrayList<>();
    private BuildingAdapter adapter;

    IntentFilter intentFilter;
    BroadcastReceiver syncReceiver;
    @Inject
    ApiService apiService;
    FloatingActionButton mainFab, mineFab, farmFab, fakeFab;
    boolean isMainFabOpen;
    Animation mainFabRotateLeft, mainFabRotateRight, appearSmallFab, disappearSmallFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.buildings_overview_layout, container, false);
        DaggerMainActivityComponent.builder().build().inject(this);
        intentFilter = new IntentFilter(SyncService.SYNC_DONE);
        syncReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("SyncReceiver", "Broadcast received");
                adapter.clear();
                BuildingsResponse syncBuildings = (BuildingsResponse) intent
                        .getExtras()
                        .getSerializable("bundle");
                adapter.addAll(syncBuildings.getBuildings());
                Toast.makeText(getActivity(), "Buildings synced", Toast.LENGTH_SHORT).show();
            }
        };
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(syncReceiver, intentFilter);
        mainFab = (FloatingActionButton) contentView.findViewById(R.id.mainFab);
        mineFab = (FloatingActionButton) contentView.findViewById(R.id.mineFab);
        farmFab = (FloatingActionButton) contentView.findViewById(R.id.farmFab);
        fakeFab = (FloatingActionButton) contentView.findViewById(R.id.fakeDownloadFab);
        isMainFabOpen = false;
        mainFabRotateLeft = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_main_fab_left);
        mainFabRotateRight = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_main_fab_right);
        appearSmallFab = AnimationUtils.loadAnimation(getContext(), R.anim.appear_small_fab);
        disappearSmallFab = AnimationUtils.loadAnimation(getContext(), R.anim.disappear_small_fab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAndCloseFabs();
            }
        });
        fakeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSyncService(v);
                openAndCloseFabs();
            }
        });
        ListView listView = (ListView) contentView.findViewById(R.id.listViewBuilding);
        adapter = new BuildingAdapter(container.getContext(), buildings);
        listView.setAdapter(adapter);
        apiService.getBuildings(1).enqueue(new Callback<BuildingsResponse>() {
            @Override
            public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                adapter.clear();
                adapter.addAll(response.body().getBuildings());
            }
            @Override
            public void onFailure(Call<BuildingsResponse> call, Throwable t) {
            }
        });
        return contentView;
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(syncReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(syncReceiver, intentFilter);
    }

    private void openAndCloseFabs() {
        if (isMainFabOpen) {
            mainFab.startAnimation(mainFabRotateLeft);
            mineFab.startAnimation(disappearSmallFab);
            farmFab.startAnimation(disappearSmallFab);
            fakeFab.startAnimation(disappearSmallFab);
            isMainFabOpen = false;
        } else {
            mainFab.startAnimation(mainFabRotateRight);
            mineFab.startAnimation(appearSmallFab);
            farmFab.startAnimation(appearSmallFab);
            fakeFab.startAnimation(appearSmallFab);
            isMainFabOpen = true;
        }
        mineFab.setClickable(isMainFabOpen);
        farmFab.setClickable(isMainFabOpen);
        fakeFab.setClickable(isMainFabOpen);
    }

    private void startSyncService(View v) {
    Intent intent = new Intent(getActivity(), SyncService.class);
     getActivity().startService(intent);
 }
}
