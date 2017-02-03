package com.greenfox.peridot.peridot_coz_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.greenfox.peridot.peridot_coz_android.R;
import com.greenfox.peridot.peridot_coz_android.adapter.TroopAdapter;
import com.greenfox.peridot.peridot_coz_android.dagger.DaggerMainActivityComponent;
import com.greenfox.peridot.peridot_coz_android.model.api.ApiService;
import com.greenfox.peridot.peridot_coz_android.model.pojo.Troop;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TroopsOverviewFragment extends Fragment {

    ListView troopsList;
    Troop troop;
    ArrayList<Troop> troops;
    TroopAdapter troopAdapter;
    @Inject
    ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DaggerMainActivityComponent.builder().build().inject(this);
        View contentView = inflater.inflate(R.layout.troops_overview_layout, container, false);

        troopsList = (ListView) contentView.findViewById(R.id.troopsList);
        troops = new ArrayList<>();

        troopAdapter = new TroopAdapter(container.getContext(), troops);
        troopsList.setAdapter(troopAdapter);


        apiService.getTroops(1).enqueue(new Callback<ArrayList<Troop>>() {
            @Override
            public void onResponse(Call<ArrayList<Troop>> call, Response<ArrayList<Troop>> response) {
                troopAdapter.clear();
                troopAdapter.addAll(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<Troop>> call, Throwable t) {
            }});
        return contentView;
    }
}
