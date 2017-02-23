package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.greenfox.peridot.peridot_coz_android.R;
import android.widget.ListView;
import com.greenfox.peridot.peridot_coz_android.adapter.TroopAdapter;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;
import com.greenfox.peridot.peridot_coz_android.model.response.TroopsResponse;
import com.greenfox.peridot.peridot_coz_android.provider.DaggerServiceComponent;
import com.greenfox.peridot.peridot_coz_android.provider.Services;

import java.util.ArrayList;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TroopsOverviewFragment extends BaseFragment {

    ListView troopsList;
    Troop troop;
    private ArrayList<Troop> troops = new ArrayList<>();
    private TroopAdapter troopAdapter;
    @Inject
    Services services;

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
            public void onFailure(Call<TroopsResponse> call, Throwable t) {
            }});
        return contentView;
    }

    @Override
    public void onData(Call call, Response response) {

    }

    @Override
    public void onError(Call call, Throwable t) {

    }
}
