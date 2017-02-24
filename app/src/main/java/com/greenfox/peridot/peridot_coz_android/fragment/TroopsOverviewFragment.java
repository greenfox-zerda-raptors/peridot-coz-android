package com.greenfox.peridot.peridot_coz_android.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.greenfox.peridot.peridot_coz_android.CozApp;
import com.greenfox.peridot.peridot_coz_android.R;
import android.widget.AdapterView;
import android.widget.ListView;
import com.greenfox.peridot.peridot_coz_android.adapter.TroopAdapter;
import com.greenfox.peridot.peridot_coz_android.backgroundSync.TroopsEvent;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;
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

public class TroopsOverviewFragment extends BaseFragment {

    ListView troopsList;
    private ArrayList<Troop> troops = new ArrayList<>();
    private TroopAdapter troopAdapter;
    @Inject
    Services services;
    private int counter = 50;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerServiceComponent.builder().build().inject(this);

        View contentView = inflater.inflate(R.layout.troops_overview_layout, container, false);

        troopsList = (ListView) contentView.findViewById(R.id.troopsList);

        troopAdapter = new TroopAdapter(container.getContext(), new ArrayList<Troop>());
        troopsList.setAdapter(troopAdapter);
        services.apiService.getTroops().enqueue(this);
        troopsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundles = new Bundle();
                Troop troop = (Troop) troopsList.getAdapter().getItem(position);
                bundles.putSerializable("troop", troop);
                TroopDetailFragment frag = new TroopDetailFragment();
                frag.setArguments(bundles);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, frag)
                        .addToBackStack(null)
                        .commit();
            }
        }) ;
        saveTroopCountToSharedPreferences();
        return contentView;
    }

    @Override
    public void onData(Call call, Response response) {
        TroopsResponse troopsResponse = (TroopsResponse) response.body();
        troopAdapter.clear();
        troopAdapter.addAll(troopsResponse.getTroops());

    }

    @Override
    public void onError(Call call, Throwable t) {

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        saveTroopCountToSharedPreferences();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        saveTroopCountToSharedPreferences();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
        saveTroopCountToSharedPreferences();
    }

    @Subscribe
    public void onTroopsEvent(TroopsEvent troopsEvent) {
        services.apiService.getTroops().enqueue(new Callback<TroopsResponse>() {
            @Override
            public void onResponse(Call<TroopsResponse> call, Response<TroopsResponse> response) {
                troopAdapter.clear();
                troopAdapter.addAll(response.body().getTroops());
            }
            @Override
            public void onFailure(Call<TroopsResponse> call, Throwable t) {}
        });
        Vibrator vibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }

    private void saveTroopCountToSharedPreferences() {
        SharedPreferences troopCount = CozApp.getApplication().getSharedPreferences("troops", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = troopCount.edit();
        editor.putInt("troops", troopAdapter.getCount());
        editor.apply();
    }
}

