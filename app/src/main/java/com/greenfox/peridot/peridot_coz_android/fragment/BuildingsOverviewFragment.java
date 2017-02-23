package com.greenfox.peridot.peridot_coz_android.fragment;

<<<<<<< HEAD
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
=======
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
>>>>>>> master
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.BuildingAdapter;
<<<<<<< HEAD
import android.widget.Toast;
import com.greenfox.peridot.peridot_coz_android.backgroundSync.SyncService;
=======
import com.greenfox.peridot.peridot_coz_android.backgroundSync.BuildingsEvent;
>>>>>>> master
import com.greenfox.peridot.peridot_coz_android.model.pojo.Building;
import com.greenfox.peridot.peridot_coz_android.model.response.BuildingsResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;
<<<<<<< HEAD
=======
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
>>>>>>> master
import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
<<<<<<< HEAD

public class BuildingsOverviewFragment extends BaseFragment {
=======
import static android.content.Context.VIBRATOR_SERVICE;

public class BuildingsOverviewFragment extends Fragment {
>>>>>>> master

    private ArrayList<Building> buildings = new ArrayList<>();
    private BuildingAdapter adapter;
    private int counter = 164;
<<<<<<< HEAD

    IntentFilter intentFilter;
    BroadcastReceiver syncReceiver;
=======
>>>>>>> master
    @Inject
    Services services;
    FloatingActionButton mainFab, mineFab, farmFab, barrackFab, townhallFab, fakeFab;
    boolean isMainFabOpen;
    Animation mainFabRotateLeft, mainFabRotateRight, appearSmallFab, disappearSmallFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.buildings_overview_layout, container, false);
        DaggerServiceComponent.builder().build().inject(this);
<<<<<<< HEAD
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
=======
>>>>>>> master
        mainFab = (FloatingActionButton) contentView.findViewById(R.id.mainFab);
        mineFab = (FloatingActionButton) contentView.findViewById(R.id.mineFab);
        farmFab = (FloatingActionButton) contentView.findViewById(R.id.farmFab);
        barrackFab = (FloatingActionButton) contentView.findViewById(R.id.barrackFab);
        townhallFab = (FloatingActionButton) contentView.findViewById(R.id.townhallFab);
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
<<<<<<< HEAD
                startSyncService(v);
=======
>>>>>>> master
                openAndCloseFabs();
            }
        });
        mineFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building mine = new Building(counter, "Mine");
                counter++;
                overrideApi(mine);
            }
        });
        farmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building farm = new Building(counter, "Farm");
                counter++;
                overrideApi(farm);
            }
        });
        barrackFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building barrack = new Building(counter, "Barrack");
                counter++;
                overrideApi(barrack);
            }
        });
        townhallFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Building townhall = new Building(counter, "Townhall");
                counter++;
                overrideApi(townhall);
            }
        });
        final ListView listView = (ListView) contentView.findViewById(R.id.listViewBuilding);
        adapter = new BuildingAdapter(container.getContext(), buildings);
        listView.setAdapter(adapter);
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
        }) ;
<<<<<<< HEAD
        return contentView;
    }

    @Override
    public void onData(Call call, Response response) {

    }

    @Override
    public void onError(Call call, Throwable t) {

=======
    return contentView;
}

    private void overrideApi(final Building building) {
        services.apiService.createBuilding(building).enqueue(new Callback<Building>() {
            @Override
            public void onResponse(Call<Building> call, Response<Building> response) {
                adapter.add(response.body());
            }
            @Override
            public void onFailure(Call<Building> call, Throwable t) {}
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
>>>>>>> master
    }

    @Override
    public void onPause() {
        super.onPause();
<<<<<<< HEAD
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(syncReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(syncReceiver, intentFilter);
    }

    private void overrideApi(final Building building) {
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
=======
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

>>>>>>> master
    private void openAndCloseFabs() {
        if (isMainFabOpen) {
            mainFab.startAnimation(mainFabRotateLeft);
            mineFab.startAnimation(disappearSmallFab);
            farmFab.startAnimation(disappearSmallFab);
            barrackFab.startAnimation(disappearSmallFab);
            townhallFab.startAnimation(disappearSmallFab);
            fakeFab.startAnimation(disappearSmallFab);
            isMainFabOpen = false;
        } else {
            mainFab.startAnimation(mainFabRotateRight);
            mineFab.startAnimation(appearSmallFab);
            farmFab.startAnimation(appearSmallFab);
            barrackFab.startAnimation(appearSmallFab);
            townhallFab.startAnimation(appearSmallFab);
            fakeFab.startAnimation(appearSmallFab);
            isMainFabOpen = true;
        }
        mineFab.setClickable(isMainFabOpen);
        farmFab.setClickable(isMainFabOpen);
        barrackFab.setClickable(isMainFabOpen);
        townhallFab.setClickable(isMainFabOpen);
        fakeFab.setClickable(isMainFabOpen);
<<<<<<< HEAD
    }

    private void startSyncService(View v) {
        Intent intent = new Intent(getActivity(), SyncService.class);
        getActivity().startService(intent);
=======
   }

    @Subscribe
    private void onBuildingsEvent(BuildingsEvent buildingsEvent) {
            services.apiService.getBuildings().enqueue(new Callback<BuildingsResponse>() {
                @Override
                public void onResponse(Call<BuildingsResponse> call, Response<BuildingsResponse> response) {
                    adapter.clear();
                    adapter.addAll(response.body().getBuildings());
                }
                @Override
                public void onFailure(Call<BuildingsResponse> call, Throwable t) {}
            });
            Vibrator vibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(500);
>>>>>>> master
    }
}
