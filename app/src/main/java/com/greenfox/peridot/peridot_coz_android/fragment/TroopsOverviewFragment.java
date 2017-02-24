package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TroopsOverviewFragment extends Fragment {

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
        troopAdapter = new TroopAdapter(container.getContext(), troops);
        troopsList.setAdapter(troopAdapter);
        services.apiService.getTroops().enqueue(new Callback<TroopsResponse>() {
            @Override
            public void onResponse(Call<TroopsResponse> call, Response<TroopsResponse> response) {
                troopAdapter.clear();
                troopAdapter.addAll(response.body().getTroops());
            }
            @Override
            public void onFailure(Call<TroopsResponse> call, Throwable t) {}
        });
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
        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
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
}
